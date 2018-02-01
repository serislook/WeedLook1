package com.skyweednet.weedlook.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.google.firebase.database.Query;
import com.skyweednet.weedlook.R;
import com.skyweednet.weedlook.data.CurrentUser;
import com.skyweednet.weedlook.data.EmailProcessor;
import com.skyweednet.weedlook.models.Sample;
import com.squareup.picasso.Picasso;

/**
 * Created by osx on 06-10-17.
 */

public class SamplesAdapter extends FirebaseRecyclerAdapter<Sample, SamplesAdapter.SampleHolder> {

    private SamplesListener listener;
    private boolean first = true;
    private static final String USER_EMAIL = new EmailProcessor().sanitizedEmail(new CurrentUser().email());


    public SamplesAdapter(SamplesListener listener, Query ref) {
        super(Sample.class, R.layout.list_item_sample, SampleHolder.class, ref);
        this.listener = listener;
    }

    @Override
    protected void populateViewHolder(final SampleHolder viewHolder, Sample model, int position) {

        viewHolder.name.setText(model.getName());
        viewHolder.category.setText(model.getCategory());
        viewHolder.flowering.setText(model.getFlowering());


        if (!model.getImage().isEmpty()) {

            Picasso.with(viewHolder.itemView.getContext()).load(model.getImage()).into(viewHolder.imageView);
        }


        viewHolder.deletesample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sample auxSample = getItem(viewHolder.getAdapterPosition());
                listener.clicked(auxSample);
            }
        });

        viewHolder.editsample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sample auxSample = getItem(viewHolder.getAdapterPosition());
                listener.clickededit(auxSample);
            }
        });


        viewHolder.tasting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sample auxSample = getItem(viewHolder.getAdapterPosition());
                listener.tasting(auxSample);

            }
        });

        TextView shareBtn = viewHolder.shared;
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sample auxSample = getItem(viewHolder.getAdapterPosition());
                listener.shared(auxSample);

            }
        });

        //TODO do the same for the other UI elements
        if (USER_EMAIL.equals(model.getOwner())) {
            shareBtn.setVisibility(View.GONE);
        } else {
            shareBtn.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onDataChanged() {
        super.onDataChanged();

        if (first) {
            first = false;
            listener.dataChanged();
        } else {
            listener.add();
        }
    }

    public static class SampleHolder extends RecyclerView.ViewHolder {

        private TextView name, category, flowering, deletesample, editsample, tasting, shared;
        private RoundedImageView imageView;

        public SampleHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.nameTv);
            category = (TextView) itemView.findViewById(R.id.categoryTv);
            flowering = (TextView) itemView.findViewById(R.id.floweringTv);
            deletesample = (TextView) itemView.findViewById(R.id.deletesample);
            editsample = (TextView) itemView.findViewById(R.id.editsample);
            imageView = (RoundedImageView) itemView.findViewById(R.id.imageView);
            tasting = (TextView) itemView.findViewById(R.id.tastesample);
            shared = (TextView) itemView.findViewById(R.id.sharesample);

        }
    }

}
