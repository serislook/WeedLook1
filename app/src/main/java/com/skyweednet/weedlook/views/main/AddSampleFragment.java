package com.skyweednet.weedlook.views.main;


import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddSampleFragment extends Fragment {

    protected EditText name, category, flowering;
    protected RoundedImageView imagesample;
    private TextView addsample;
    protected String path, pathurl = "";
    protected String key = null;

    private MagicalPermissions magicalPermissions;
    private MagicalCamera magicalCamera;
    private int PHOTO_SIZE = 15;


    public AddSampleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_sample, container, false);
    }

    @CallSuper
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] permissions = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };


        magicalPermissions = new MagicalPermissions(this, permissions);
        magicalCamera = new MagicalCamera(getActivity(), PHOTO_SIZE, magicalPermissions);

        name = (EditText) view.findViewById(R.id.nameEt);
        category = (EditText) view.findViewById(R.id.categoryEt);
        flowering = (EditText) view.findViewById(R.id.floweringEt);
        imagesample = (RoundedImageView) view.findViewById(R.id.imagesample);

        imagesample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                magicalCamera.takeFragmentPhoto(AddSampleFragment.this);

            }
        });

        addsample = (TextView) view.findViewById(R.id.addsample);

        addsample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameTx = name.getText().toString().trim();
                String categoryTx = category.getText().toString().trim();
                String floweringTx = flowering.getText().toString().trim();

                final CurrentUser currentUser = new CurrentUser();
                //TODO porqué más diagonal
                String email = new EmailProcessor().sanitizedEmail(currentUser.email() + "/");

                if (!nameTx.isEmpty() && !categoryTx.isEmpty() && !floweringTx.isEmpty() && !pathurl.isEmpty()) {

                    key = (key != null) ? key : new Nodes().sample(email).push().getKey();

                    Sample sample = new Sample();
                    sample.setName(nameTx);
                    sample.setCategory(categoryTx);
                    sample.setFlowering(floweringTx);
                    sample.setImage(pathurl);
                    sample.setKey(key);
                    sample.setOwner(email);


                    new Nodes().sample(email).child(key).setValue(sample);

                    View layout = getView();
                    layout.setVisibility(View.GONE);

                    name.setText("");
                    category.setText("");
                    flowering.setText("");
                    Picasso.with(getContext()).load(R.drawable.icons8_add_image_80).centerCrop().fit().into(imagesample);

                    Toast.makeText(getActivity(), "Muestra agregada con éxito", Toast.LENGTH_SHORT).show();

                    getActivity().finish();


                } else {
                    Toast.makeText(getActivity(), "Por favor, complete todos los datos", Toast.LENGTH_SHORT).show();


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
            toFirebasePathSample(path);

        }


    }

    private void setPhoto(String url) {
        Picasso.with(getContext()).load(url).centerCrop().fit().into(imagesample);
    }

    public void toFirebasePathSample(String path) {

        final CurrentUser currentUser = new CurrentUser();
        String folder = new EmailProcessor().sanitizedEmail(currentUser.email() + "/");
        long epoch = System.currentTimeMillis() / 1000;
        String photoName = String.valueOf(epoch) + ".jpg";
        String baseUrl = "gs://weedlook1.appspot.com/samples";
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
