package com.skyweednet.weedlook.views.tastings;

import android.util.Log;
import android.widget.EditText;

import com.skyweednet.weedlook.models.Tasting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by osx on 16-12-17.
 */

public class UploadSample {

    public void byViews(List<EditText> editTexts) {

        Tasting tasting = new Tasting();
        Map<String, Double> characteristics = new HashMap<>();
        final int size = editTexts.size();
        double average = 0;
        for (EditText editText : editTexts) {
            String property = (String) editText.getTag();
            double value = 1;
            //String valueS = editText.getText().toString();
            //value = (!valueS.equals("")) ? Double.parseDouble(valueS) : value;
            try {
                value = Double.parseDouble(editText.getText().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            Log.d(property, String.valueOf(value));
            characteristics.put(property, value);
            average += value;
        }
        average = average/size;
        Log.e("Promedio",String.valueOf(average));
        tasting.setAverage(average);
        tasting.setCharacteristics(characteristics);
        //TODO add missing attributes to the model and send it to the database

    }
}
