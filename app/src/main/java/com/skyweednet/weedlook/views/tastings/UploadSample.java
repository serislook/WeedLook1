package com.skyweednet.weedlook.views.tastings;

import android.util.Log;
import android.widget.EditText;

import com.skyweednet.weedlook.data.CurrentUser;
import com.skyweednet.weedlook.data.EmailProcessor;
import com.skyweednet.weedlook.data.Nodes;
import com.skyweednet.weedlook.models.Sample;
import com.skyweednet.weedlook.models.Score;
import com.skyweednet.weedlook.models.Tasting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by osx on 16-12-17.
 */

public class UploadSample {

    public Tasting byViews(List<EditText> editTexts, Sample sample) {

        Tasting tasting = new Tasting();
        Map<String, Double> characteristics = new HashMap<>();
        final int size = editTexts.size();
        double average = 0;
        double finalaverage = 0;
        for (EditText editText : editTexts) {
            String property = (String) editText.getTag();
            double value = 1;
            //String valueS = editText.getText().toString();
            //value = (!valueS.equals("")) ? Double.parseDouble(valueS) : value;
            try {
                if (!editText.getText().toString().isEmpty())
                value = Double.parseDouble(editText.getText().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            Log.d(property, String.valueOf(value));
            characteristics.put(property, value);
            average += value;
        }
        average = average/size;
        finalaverage = Double.parseDouble(String.format("%.2f", average).replace(",","."));
        Log.e("Promedio",String.valueOf(average));
        Log.e("Promedio-Final",String.valueOf(finalaverage));
        tasting.setAverage(finalaverage);
        tasting.setCharacteristics(characteristics);

        final CurrentUser currentUser = new CurrentUser();
        String email = new EmailProcessor().sanitizedEmail(currentUser.email());

        tasting.setOwner(sample.getOwner());

        String key = new Nodes().tasting(email).push().getKey();
        String scorekey = new Nodes().scorebysamplekey(sample.getKey(),key).push().getKey();

        tasting.setKey(key);
        tasting.setSampleKey(sample.getKey());
        tasting.setScoreKey(scorekey);

        new Nodes().tasting(email).child(key).setValue(tasting);
        new Nodes().samplesharedbyemailbykey(email,sample.getKey()).removeValue();

        if (!email.equals(tasting.getOwner())){
            Score score = new Score();
            score.setKey(scorekey);
            score.setOwner(email);
            score.setSamplekey(sample.getKey());
            score.setAverage(finalaverage);
            score.setSampleowner(sample.getOwner());

            new Nodes().scorebysamplekey(sample.getKey(),scorekey).setValue(score);

        }




        return tasting;

    }
}
