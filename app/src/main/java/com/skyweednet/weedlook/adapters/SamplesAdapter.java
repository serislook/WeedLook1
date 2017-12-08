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
 * Created by osx on 06-10-17.
 */

public class SamplesAdapter extends FirebaseRecyclerAdapter<Sample,SamplesAdapter.SampleHolder> {

    private SamplesListener listener;
    private boolean first = true;


    public SamplesAdapter(SamplesListener listener, String email) {
        super(Sample.class, R.layout.list_item_sample, SampleHolder.class, new Nodes().samplebyemail(email));
        this.listener = listener;
    }

    @Override
    protected void populateViewHolder(final SampleHolder viewHolder, Sample model, int position) {

        viewHolder.name.setText(model.getName());
        viewHolder.category.setText(model.getCategory());
        viewHolder.flowering_time.setText(model.getFlowering_time());

        if (!model.getImage().isEmpty()){

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





    }

    @Override
    protected void onDataChanged() {
        super.onDataChanged();

        if (first){
            first = false;
            listener.dataChanged();
        }else{
            listener.add();
        }
    }

    public static class SampleHolder extends RecyclerView.ViewHolder {

        private TextView name,category,flowering_time,deletesample,editsample;
        private RoundedImageView imageView;

        public SampleHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.nameTv);
            category = (TextView) itemView.findViewById(R.id.categoryTv);
            flowering_time = (TextView) itemView.findViewById(R.id.floweringTv);
            deletesample = (TextView) itemView.findViewById(R.id.deletesample);
            editsample = (TextView) itemView.findViewById(R.id.editsample);
            imageView = (RoundedImageView) itemView.findViewById(R.id.imageView);
        }
    }

}
