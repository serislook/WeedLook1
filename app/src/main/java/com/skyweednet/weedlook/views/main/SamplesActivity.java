package com.skyweednet.weedlook.views.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.skyweednet.weedlook.R;

/**
 * Created by osx on 28-11-17.
 */

public class SamplesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samples);

        TextView addsample = (TextView) findViewById(R.id.addsample);

        View layout = findViewById(R.id.FragmentAddsample);
        layout.setVisibility(View.GONE);

        addsample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View layout = findViewById(R.id.FragmentAddsample);
                if (layout.getVisibility() == View.VISIBLE){

                    layout.setVisibility(View.GONE);

                }else{
                    layout.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
