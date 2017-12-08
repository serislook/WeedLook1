package com.skyweednet.weedlook.views.add;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.skyweednet.weedlook.R;
import com.skyweednet.weedlook.views.tastings.Tastings;

public class AddSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sample);

        TextView textView = (TextView) findViewById(R.id.tastesample);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddSampleActivity.this, Tastings.class);
                startActivity(intent);
            }
        });

    }
}
