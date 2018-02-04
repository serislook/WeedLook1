package com.skyweednet.weedlook.views.tasterguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skyweednet.weedlook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FraganceFragment extends Fragment {


    public FraganceFragment() {
        // Required empty public constructor
    }
    public static FraganceFragment newInstance(){
        return new FraganceFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragance, container, false);
    }

}
