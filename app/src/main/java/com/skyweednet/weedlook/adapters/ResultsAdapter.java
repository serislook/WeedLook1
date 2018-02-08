package com.skyweednet.weedlook.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.skyweednet.weedlook.R;
import com.skyweednet.weedlook.data.Nodes;
import com.skyweednet.weedlook.models.Sample;
import com.skyweednet.weedlook.models.Score;
import com.squareup.picasso.Picasso;

/**
 * Created by osx on 07-02-18.
 */

public class ResultsAdapter extends FirebaseRecyclerAdapter<Score, ResultsAdapter.ResultsHolder> {

    private ResultsListener listener;
    private boolean first = true;
    private String email;

    public ResultsAdapter(ResultsListener listener,String email) {
        super(Score.class, R.layout.list_item_tasting, ResultsHolder.class, new Nodes().getscores());
        this.listener = listener;
        this.email = email;
    }


    @Override
    protected void populateViewHolder(final ResultsHolder viewHolder, Score model, int position) {
        viewHolder.name.setVisibility(View.GONE);
        viewHolder.deletetasting.setVisibility(View.GONE);

        new Nodes().samplebyemailbykey(model.getSampleowner(),model.getSamplekey()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Sample sample = dataSnapshot.getValue(Sample.class);
                if (sample!=null){
                    //viewHolder.name.setText("Nombre:  "+sample.getName());
                    viewHolder.flowering.setText("Tiempo de Floración:  "+String.valueOf(sample.getFlowering()));
                    Picasso.with(viewHolder.itemView.getContext()).load(sample.getImage()).resize(800,600).into(viewHolder.imageView);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        viewHolder.average.setText("Promedio:  "+String.valueOf(model.getAverage()));


        viewHolder.deletetasting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    public static class ResultsHolder extends RecyclerView.ViewHolder {

        private TextView name, average, flowering, deletetasting, tasteanother;
        private RoundedImageView imageView;


        public ResultsHolder(View itemView) {
            super(itemView);


            name = (TextView) itemView.findViewById(R.id.namesampleTv);
            average = (TextView) itemView.findViewById(R.id.averageTv);
            flowering = (TextView) itemView.findViewById(R.id.floweringtimeTv);
            imageView = (RoundedImageView) itemView.findViewById(R.id.sampleIv);
            deletetasting = (TextView) itemView.findViewById(R.id.deletetastingTv);
            //tasteanother = (TextView) itemView.findViewById(R.id.tasteanotherTv);



        }
    }

}
