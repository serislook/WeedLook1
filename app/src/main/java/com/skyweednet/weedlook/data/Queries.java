package com.skyweednet.weedlook.data;

import com.skyweednet.weedlook.models.Samples;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by osx on 06-10-17.
 */


public class Queries {

    public List<Samples> samples() {

        List<Samples> samples = new ArrayList<>();
       /* List<Samples> samplesList = Samples.listALL(Samples.class);
        if (samples != null && samplesList.size() >0) {
            samples.addAll(samplesList);

        }*/

        return samples;
    }


}




