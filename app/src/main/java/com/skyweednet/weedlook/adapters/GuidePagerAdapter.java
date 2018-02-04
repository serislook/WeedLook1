package com.skyweednet.weedlook.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.skyweednet.weedlook.views.tasterguide.EffectFragment;
import com.skyweednet.weedlook.views.tasterguide.FlavorFragment;
import com.skyweednet.weedlook.views.tasterguide.FraganceFragment;
import com.skyweednet.weedlook.views.tasterguide.PresenceFragment;

/**
 * Created by osx on 03-02-18.
 */
public class GuidePagerAdapter extends FragmentPagerAdapter {

    public GuidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PresenceFragment.newInstance();
            case 1:
                return FraganceFragment.newInstance();
            case 2:
                return FlavorFragment.newInstance();
            case 3:
                return EffectFragment.newInstance();
            default:
                return PresenceFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Presencia";
            case 1:
                return "Aroma";
            case 2:
                return "Sabor";
            case 3:
                return "Efecto";
        }
        return null;
    }
}
