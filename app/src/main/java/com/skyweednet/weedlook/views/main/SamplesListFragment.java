package com.skyweednet.weedlook.views.main;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.skyweednet.weedlook.R;
import com.skyweednet.weedlook.adapters.SamplesAdapter;
import com.skyweednet.weedlook.adapters.SamplesListener;
import com.skyweednet.weedlook.data.CurrentUser;
import com.skyweednet.weedlook.data.EmailProcessor;
import com.skyweednet.weedlook.data.Nodes;
import com.skyweednet.weedlook.models.Sample;
import com.skyweednet.weedlook.views.tastings.TastingsActivity;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class SamplesListFragment extends Fragment implements SamplesListener {

    public static final String SAMPLE = "com.skyweednet.weedlook.views.main.KEY.SAMPLE";


    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private SamplesAdapter adapter;


    public SamplesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_samples_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Actualizando... Espere por favor");
        progressDialog.show();

        recyclerView = (RecyclerView) view.findViewById(R.id.samplesRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final CurrentUser currentUser = new CurrentUser();
        String email = new EmailProcessor().sanitizedEmail(currentUser.email() + "/");

        adapter = new SamplesAdapter(this, email);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void clicked(final Sample sample) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage("¿Desea eliminar esta muestra de la lista?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Sí",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Toast.makeText(getApplicationContext(), sample.getCategory() + " eliminado de tus muestras", Toast.LENGTH_SHORT).show();
                        CurrentUser currentUser = new CurrentUser();
                        String key = new EmailProcessor().sanitizedEmail(currentUser.email() + "/");

                        new Nodes().sample(key).child(sample.getKey()).removeValue();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();


    }

    @Override
    public void clickededit(Sample sample) {
        Intent intent = new Intent(getActivity(), EditSamplesActivity.class);
        intent.putExtra(SAMPLE, sample);
        startActivity(intent);
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
    public void tasting(Sample sample) {

        Intent intent = new Intent(getActivity(), TastingsActivity.class);
        intent.putExtra("SAMPLE_KEY", sample);  // pass your values and retrieve them in the other Activity using keyName
        startActivity(intent);

    }


}
