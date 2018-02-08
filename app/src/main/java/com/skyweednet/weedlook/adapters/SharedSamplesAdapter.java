package com.skyweednet.weedlook.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.skyweednet.weedlook.R;
import com.skyweednet.weedlook.data.Nodes;
import com.skyweednet.weedlook.models.Sample;
import com.squareup.picasso.Picasso;

/**
 * Created by osx on 31-01-18.
 */

public class SharedSamplesAdapter extends FirebaseRecyclerAdapter<Sample, SharedSamplesAdapter.SampleHolder> {

    private SharedSamplesListener listener;
    private boolean first = true;

    public SharedSamplesAdapter(SharedSamplesListener listener, String email) {
        super(Sample.class, R.layout.list_item_sample_shared, SampleHolder.class, new Nodes().sharedsamplebyemail(email));
        this.listener = listener;
    }

    @Override
    protected void populateViewHolder(final SampleHolder viewHolder, Sample model, int position) {

        viewHolder.name.setText(model.getName());
        viewHolder.name.setVisibility(View.GONE);
        viewHolder.category.setText("Categoría:  "+model.getCategory());
        viewHolder.flowering.setText("Tiempo de Floración:  "+model.getFlowering());


        if (!model.getImage().isEmpty()) {

            Picasso.with(viewHolder.itemView.getContext()).load(model.getImage()).resize(800,600).into(viewHolder.imageView);
        }

        viewHolder.tasting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sample auxSample = getItem(viewHolder.getAdapterPosition());
                listener.tasting(auxSample);

            }
        });

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

        private TextView name, category, flowering, tasting;
        private RoundedImageView imageView;

        public SampleHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.nameTv);
            category = (TextView) itemView.findViewById(R.id.categoryTv);
            flowering = (TextView) itemView.findViewById(R.id.floweringTv);
            imageView = (RoundedImageView) itemView.findViewById(R.id.imageView);
            tasting = (TextView) itemView.findViewById(R.id.tastesample);

        }
    }

}
