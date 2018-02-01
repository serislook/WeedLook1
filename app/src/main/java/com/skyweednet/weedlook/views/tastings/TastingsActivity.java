package com.skyweednet.weedlook.views.tastings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.RoundedImageView;
import com.skyweednet.weedlook.R;
import com.skyweednet.weedlook.models.Sample;
import com.skyweednet.weedlook.models.Tasting;
import com.skyweednet.weedlook.views.mytastings.MyTastingsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TastingsActivity extends AppCompatActivity {

    private RoundedImageView imageView;
    private TextView textView, presenceTv, manicureTv, maturityTv, curedTv, seedsTv, resinTv, pestsTv, fraganceTv, exitnoteTv, heartnoteTv, backgroundnotesTv, flavorTv, drytastingTv, tastingincombustionTv, smokeTv, efectTv, thcTv, cbdTv, insideTv, powerTv;
    private EditText editText, manicureEt, maturityEt, curedEt, seedsEt, resinEt, pestsEt, exitnoteEt, heartnoteEt, backgroundnotesEt, drytastingEt, tastingincombustionEt, smokeEt, thcEt, cbdEt, insideEt, powerEt;
    private View line;
    private Button button;

    public static final String SAMPLE = "com.skyweednet.weedlook.views.mytastings.KEY.SAMPLE";
    public static final String TASTING = "com.skyweednet.weedlook.views.mytastings.KEY.SAMPLE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasting);

        final Sample sample = (Sample) getIntent().getSerializableExtra("SAMPLE_KEY");
        Log.e("SAMPLE", sample.getName());

        imageView = (RoundedImageView) findViewById(R.id.imageIv);

        presenceTv = (TextView) findViewById(R.id.presenceTv);

        manicureTv = (TextView) findViewById(R.id.manicureTv);
        manicureEt = (EditText) findViewById(R.id.manicureEt);

        maturityTv = (TextView) findViewById(R.id.maturityTv);
        maturityEt = (EditText) findViewById(R.id.maturityEt);

        curedTv = (TextView) findViewById(R.id.curedTv);
        curedEt = (EditText) findViewById(R.id.curedEt);

        seedsTv = (TextView) findViewById(R.id.seedsTv);
        seedsEt = (EditText) findViewById(R.id.seedsEt);

        resinTv = (TextView) findViewById(R.id.resinTv);
        resinEt = (EditText) findViewById(R.id.resinEt);

        pestsTv = (TextView) findViewById(R.id.pestsTv);
        pestsEt = (EditText) findViewById(R.id.pestsEt);

        line = findViewById(R.id.line1);

        fraganceTv = (TextView) findViewById(R.id.fraganceTv);

        exitnoteTv = (TextView) findViewById(R.id.exitnoteTv);
        exitnoteEt = (EditText) findViewById(R.id.exitnoteEt);

        heartnoteTv = (TextView) findViewById(R.id.heartnoteTv);
        heartnoteEt = (EditText) findViewById(R.id.heartnoteEt);

        backgroundnotesTv = (TextView) findViewById(R.id.backgroundnotesTv);
        backgroundnotesEt = (EditText) findViewById(R.id.backgroundnotesEt);

        line = findViewById(R.id.line2);

        flavorTv = (TextView) findViewById(R.id.flavorTv);

        drytastingTv = (TextView) findViewById(R.id.drytastingTv);
        drytastingEt = (EditText) findViewById(R.id.drytastingEt);

        tastingincombustionTv = (TextView) findViewById(R.id.tastingincombustionTv);
        tastingincombustionEt = (EditText) findViewById(R.id.tastingincombustionEt);

        smokeTv = (TextView) findViewById(R.id.smokeTv);
        smokeEt = (EditText) findViewById(R.id.smokeEt);

        line = findViewById(R.id.line3);

        efectTv = (TextView) findViewById(R.id.efectTv);

        thcTv = (TextView) findViewById(R.id.thcTv);
        thcEt = (EditText) findViewById(R.id.thcEt);

        cbdTv = (TextView) findViewById(R.id.cbdTv);
        cbdEt = (EditText) findViewById(R.id.cbdEt);

        insideTv = (TextView) findViewById(R.id.insideTv);
        insideEt = (EditText) findViewById(R.id.insideEt);

        powerTv = (TextView) findViewById(R.id.powerTv);
        powerEt = (EditText) findViewById(R.id.powerEt);

        button = (Button) findViewById(R.id.calificationBtn);

        Picasso.with(this).load(sample.getImage()).into(imageView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("HERE", "clicked");
                List<EditText> editTexts = new ArrayList<>();
                ViewGroup container = (ViewGroup) findViewById(R.id.fieldContainer);
                int size = container.getChildCount();
                Log.d("SIZE", String.valueOf(size));
                for (int i = 0; i < size; i++) {
                    View view = container.getChildAt(i);
                    if (view instanceof EditText && view.getTag() != null) {
                        EditText editText = (EditText) view;
                        editTexts.add(editText);
                    }
                }
                if (size>0){
                    Tasting tasting = new UploadSample().byViews(editTexts, sample);
                    Intent intent = new Intent(TastingsActivity.this, MyTastingsActivity.class);
                    intent.putExtra(SAMPLE, sample);
                    intent.putExtra(TASTING, tasting);
                    startActivity(intent);
                    finish();
                }else{
                    finish();
                }



            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
