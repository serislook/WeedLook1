package com.skyweednet.weedlook.views.results;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skyweednet.weedlook.R;
import com.skyweednet.weedlook.adapters.ResultsAdapter;
import com.skyweednet.weedlook.adapters.ResultsListener;
import com.skyweednet.weedlook.data.CurrentUser;
import com.skyweednet.weedlook.data.EmailProcessor;
import com.skyweednet.weedlook.models.Score;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListResultsFragment extends Fragment implements ResultsListener{

    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private ResultsAdapter adapter;

    public ListResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_results, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Actualizando... Espere por favor");
        progressDialog.show();

        recyclerView = (RecyclerView) view.findViewById(R.id.resultsRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final CurrentUser currentUser = new CurrentUser();
        String email = new EmailProcessor().sanitizedEmail(currentUser.email());

        adapter = new ResultsAdapter(this,email);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void dataChanged() {

        progressDialog.dismiss();

    }

    @Override
    public void add() {
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);
    }

    @Override
    public void tasting(Score tasting) {

    }
}
