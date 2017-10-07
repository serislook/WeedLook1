package com.skyweednet.weedlook.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.skyweednet.weedlook.R;
import com.skyweednet.weedlook.data.Queries;
import com.skyweednet.weedlook.models.Samples;

import java.util.List;

/**
 * Created by osx on 06-10-17.
 */

public class SamplesAdapter extends RecyclerView.Adapter<SamplesAdapter.ViewHolder>{

    private List<Samples> samples = new Queries().samples();


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_samples, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {







    }

    @Override
    public int getItemCount() {
        return samples.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView1;
        private TextView textView2;
        private CheckBox checkBox;


        public ViewHolder(View itemView) {
            super(itemView);

            textView1 = (TextView) itemView.findViewById(R.id.numberTv);
            textView2 = (TextView) itemView.findViewById(R.id.categoryTv);
            checkBox = (CheckBox) itemView.findViewById(R.id.samplesCb);
        }
    }
}
