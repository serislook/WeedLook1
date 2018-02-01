package com.skyweednet.weedlook.adapters;

import com.skyweednet.weedlook.models.Tasting;


public interface TastingsListener {

    void delete(Tasting tasting);

    void dataChanged();

    void add();

    void tasting(Tasting tasting);


}
