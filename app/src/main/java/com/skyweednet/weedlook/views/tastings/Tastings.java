package com.skyweednet.weedlook.views.tastings;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skyweednet.weedlook.R;

public class Tastings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tastings);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TextView textView = new TextView(this);
        textView.setText("");

        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.content_tastings);
        rootLayout.addView(textView);


        Header header = (Header) findViewById(R.id.header);
        header.setHeader("Exterior indica");


        Header dynamicHeader = new Header(this);
        dynamicHeader.setHeader("titulo dinamico");

        rootLayout.addView(dynamicHeader);

        final SamplesTastings samplesTastings = new SamplesTastings();
        samplesTastings.Init(this, rootLayout);
        /*samplesTesting.setTesting(R.mipmap.ic_launcher);*/

       /*   CalificationButton calificationButton = new CalificationButton(this);
        calificationButton.setBtn("Calificar");

        rootLayout.addView(calificationButton);

*/


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, samplesTastings.getAnswer(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
