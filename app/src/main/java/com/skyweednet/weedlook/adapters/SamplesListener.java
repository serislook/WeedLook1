package com.skyweednet.weedlook.adapters;

import com.skyweednet.weedlook.models.Sample;

/**
 * Created by osx on 28-11-17.
 */

public interface SamplesListener {

    void clicked(Sample sample);
    void clickededit(Sample sample);
    void dataChanged();
    void add();
}
