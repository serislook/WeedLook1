package com.skyweednet.weedlook.views.tastings;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.skyweednet.weedlook.R;

/**
 * Created by osx on 07-12-17.
 */

public class CalificationButton extends Button {



    public CalificationButton(Context context) {
        super(context);
        setAppearance();

    }
    private void setAppearance(){
        setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
        getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
    }
    public void setBtn(final String name){
        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();

            }
        });
        setText(name);
    }

}
