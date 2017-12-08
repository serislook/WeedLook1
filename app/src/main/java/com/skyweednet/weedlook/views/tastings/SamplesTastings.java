package com.skyweednet.weedlook.views.tastings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyweednet.weedlook.R;

/**
 * Created by osx on 07-12-17.
 */

public class SamplesTastings {

    private ImageView imageView;
    private TextView textView;
    private EditText editText;
    private View line;



    public void Init(Context context, ViewGroup root) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.partial_samples_tastings, root, true);
        imageView = (ImageView) view.findViewById(R.id.imageIv);

        textView = (TextView) view.findViewById(R.id.presenceTv);

        textView = (TextView) view.findViewById(R.id.manicureTv);
        editText = (EditText) view.findViewById(R.id.manicureEt);

        textView = (TextView) view.findViewById(R.id.maturityTv);
        editText = (EditText) view.findViewById(R.id.maturityEt);

        textView = (TextView) view.findViewById(R.id.curedTv);
        editText = (EditText) view.findViewById(R.id.curedEt);

        textView = (TextView) view.findViewById(R.id.seedsTv);
        editText = (EditText) view.findViewById(R.id.seedsEt);

        textView = (TextView) view.findViewById(R.id.resinTv);
        editText = (EditText) view.findViewById(R.id.resinEt);

        textView = (TextView) view.findViewById(R.id.pestsTv);
        editText = (EditText) view.findViewById(R.id.pestsEt);

        line = view.findViewById(R.id.line1);

        textView = (TextView) view.findViewById(R.id.fraganceTv);

        textView = (TextView) view.findViewById(R.id.exitnoteTv);
        editText = (EditText) view.findViewById(R.id.exitnoteEt);

        textView = (TextView) view.findViewById(R.id.heartnoteTv);
        editText = (EditText) view.findViewById(R.id.heartnoteEt);

        textView = (TextView) view.findViewById(R.id.backgroundnotesTv);
        editText = (EditText) view.findViewById(R.id.backgroundnotesEt);

        line = view.findViewById(R.id.line2);

        textView = (TextView) view.findViewById(R.id.flavorTv);

        textView = (TextView) view.findViewById(R.id.drytastingTv);
        editText = (EditText) view.findViewById(R.id.drytastingEt);

        textView = (TextView) view.findViewById(R.id.tastingincombustionTv);
        editText = (EditText) view.findViewById(R.id.tastingincombustionEt);

        textView = (TextView) view.findViewById(R.id.smokeTv);
        editText = (EditText) view.findViewById(R.id.smokeEt);

        line = view.findViewById(R.id.line3);

        textView = (TextView) view.findViewById(R.id.efectTv);

        textView = (TextView) view.findViewById(R.id.thcTv);
        editText = (EditText) view.findViewById(R.id.thcEt);

        textView = (TextView) view.findViewById(R.id.cbdTv);
        editText = (EditText) view.findViewById(R.id.cbdEt);

        textView = (TextView) view.findViewById(R.id.insideTv);
        editText = (EditText) view.findViewById(R.id.insideEt);

        textView = (TextView) view.findViewById(R.id.powerTv);
        editText = (EditText) view.findViewById(R.id.powerEt);

    }

    public void setTasting(int resourse, String califications, String hint){
        imageView.setImageResource(resourse);
        textView.setText(califications);
        editText.setHint(hint);
    }

    public String getAnswer(){
        return editText.getText().toString();

    }

}
