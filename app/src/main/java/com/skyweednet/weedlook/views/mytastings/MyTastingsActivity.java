package com.skyweednet.weedlook.views.mytastings;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.RoundedImageView;
import com.skyweednet.weedlook.R;

public class MyTastingsActivity extends AppCompatActivity {



    public static final String SAMPLE = "com.skyweednet.weedlook.views.mytastings.KEY.SAMPLE";

    private TextView name,average,flowering;
    private RoundedImageView imageView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tastings);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
