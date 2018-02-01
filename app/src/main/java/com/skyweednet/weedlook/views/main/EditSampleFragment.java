package com.skyweednet.weedlook.views.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.skyweednet.weedlook.models.Sample;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditSampleFragment extends AddSampleFragment {


    public EditSampleFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Sample sample = (Sample) getActivity().getIntent().getSerializableExtra(SamplesListFragment.SAMPLE);
        key = sample.getKey();
        super.onViewCreated(view, savedInstanceState);
        name.setText(sample.getName());
        category.setText(sample.getCategory());
        flowering.setText(sample.getFlowering());
        pathurl = sample.getImage();

        Picasso.with(imagesample.getContext()).load(sample.getImage()).resize(800,600).into(imagesample);
    }
}
