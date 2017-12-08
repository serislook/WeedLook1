package com.skyweednet.weedlook.views.tastings;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skyweednet.weedlook.R;

/**
 * Created by osx on 07-12-17.
 */

public class Header extends LinearLayout {

    private TextView titleTv;
    private LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

    public Header(Context context) {
        super(context);
        initChidls();
        setContainer();
    }

    public Header(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initChidls();
    }
    private void initChidls() {
        titleTv = new TextView(getContext());
        titleTv.setLayoutParams(layoutParams);
        titleTv.setGravity(Gravity.LEFT);
        int padding = getResources().getDimensionPixelOffset(R.dimen.activity_vertical_margin);
        titleTv.setPadding(padding, padding, padding, padding);
        titleTv.setTextSize(getResources().getDimensionPixelOffset(R.dimen.header_title_text_size));
        addView(titleTv);

    }
    public void setHeader(String title){
        titleTv.setText(title);


    }
    private void setContainer(){
        setLayoutParams(layoutParams);
        setOrientation(LinearLayout.VERTICAL);
    }
}
