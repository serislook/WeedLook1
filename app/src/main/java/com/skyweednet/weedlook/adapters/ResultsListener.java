package com.skyweednet.weedlook.adapters;

import com.skyweednet.weedlook.models.Score;

/**
 * Created by osx on 07-02-18.
 */

public interface ResultsListener {

    void dataChanged();

    void add();

    void tasting(Score tasting);
}
