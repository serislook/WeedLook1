package com.skyweednet.weedlook.adapters;

import com.skyweednet.weedlook.models.Sample;

/**
 * Created by osx on 31-01-18.
 */

public interface SharedSamplesListener {
    void tasting(Sample sample);
    void dataChanged();

    void add();
}
