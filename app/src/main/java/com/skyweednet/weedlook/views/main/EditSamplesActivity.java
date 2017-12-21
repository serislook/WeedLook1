package com.skyweednet.weedlook.views.main;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.skyweednet.weedlook.R;
import com.skyweednet.weedlook.data.CurrentUser;
import com.skyweednet.weedlook.data.EmailProcessor;
import com.skyweednet.weedlook.data.Nodes;
import com.skyweednet.weedlook.models.Sample;
import com.squareup.picasso.Picasso;

/**
 * Created by osx on 28-11-17.
 */

public class EditSamplesActivity extends AppCompatActivity {

    private TextView name, category, flowering_time, savesample;
    private RoundedImageView imagesample;
    private String key;

    private MagicalPermissions magicalPermissions;
    private MagicalCamera magicalCamera;
    private int PHOTO_SIZE = 15;
    private String path, pathurl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sample);

        Sample sample = (Sample) getIntent().getSerializableExtra(SamplesListFragment.SAMPLE);
        key = sample.getKey();

        name = (EditText) findViewById(R.id.nameEt);
        category = (EditText) findViewById(R.id.categoryEt);
        flowering_time = (EditText) findViewById(R.id.floweringEt);
        savesample = (TextView) findViewById(R.id.savesample);
        imagesample = (RoundedImageView) findViewById(R.id.imagesample);


        name.setText(sample.getName());
        category.setText(sample.getCategory());
        flowering_time.setText(sample.getFlowering_time());
        pathurl = sample.getImage();

        Picasso.with(imagesample.getContext()).load(sample.getImage()).into(imagesample);

        String[] permissions = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        magicalPermissions = new MagicalPermissions(this, permissions);
        magicalCamera = new MagicalCamera(this, PHOTO_SIZE, magicalPermissions);

        imagesample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                magicalCamera.takePhoto();

            }
        });

        savesample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameTx = name.getText().toString().trim();
                String categoryTx = category.getText().toString().trim();
                String floweringTx = flowering_time.getText().toString().trim();

                final CurrentUser currentUser = new CurrentUser();
                String email = new EmailProcessor().sanitizedEmail(currentUser.email() + "/");

                if (!nameTx.isEmpty() && !categoryTx.isEmpty() && !floweringTx.isEmpty() && !pathurl.isEmpty()) {


                    Sample sample = new Sample();
                    sample.setName(nameTx);
                    sample.setCategory(categoryTx);
                    sample.setFlowering_time(floweringTx);
                    sample.setImage(pathurl);
                    sample.setKey(key);


                    new Nodes().sample(email).child(key).setValue(sample);

                    finish();

                    Toast.makeText(EditSamplesActivity.this, "Muestra editada con éxito", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(EditSamplesActivity.this, "Por favor, complete todos los datos", Toast.LENGTH_SHORT).show();


                }

            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        magicalPermissions.permissionResult(requestCode, permissions, grantResults);

        //Following the example you could also
        //locationPermissions(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        magicalCamera.resultPhoto(requestCode, resultCode, data);


        if (RESULT_OK == resultCode) {

            Bitmap photo = magicalCamera.getPhoto();
            path = magicalCamera.savePhotoInMemoryDevice(photo, "samples", "flash", MagicalCamera.JPEG, true);

            path = "file://" + path;
            setPhoto(path);
            toFirebasePathSamples(path);

        }


    }

    private void setPhoto(String url) {
        Picasso.with(this).load(url).centerCrop().fit().into(imagesample);
    }

    public void toFirebasePathSamples(String path) {

        final CurrentUser currentUser = new CurrentUser();
        String folder = new EmailProcessor().sanitizedEmail(currentUser.email() + "/");
        long epoch = System.currentTimeMillis() / 1000;
        String photoName = String.valueOf(epoch) + ".jpg";
        String baseUrl = "gs://.weedlook1.com/samples/";
        String refUrl = baseUrl + folder + photoName;
        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(refUrl);
        storageReference.putFile(Uri.parse(path)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                @SuppressWarnings("VisibleForTests")
                String[] fullUrl = taskSnapshot.getDownloadUrl().toString().split("&token");
                String url = fullUrl[0];
                pathurl = url;
                Log.e("URL", url);

//                LocalUser user = new LocalUser();
//                user.setEmail(currentUser.email());
//                user.setName(currentUser.getCurrentuser().getDisplayName());
//                user.setPhoto(url);
//                user.setUid(currentUser.uid());
//                String key = new EmailProcessor().sanitizedEmail(currentUser.email());
//                new Nodes().user(key).setValue(user);


            }
        });
    }
}
