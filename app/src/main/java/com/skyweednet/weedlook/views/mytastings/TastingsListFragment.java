package com.skyweednet.weedlook.views.mytastings;

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
import com.skyweednet.weedlook.adapters.TastingsAdapter;
import com.skyweednet.weedlook.adapters.TastingsListener;
import com.skyweednet.weedlook.data.CurrentUser;
import com.skyweednet.weedlook.data.EmailProcessor;
import com.skyweednet.weedlook.data.Nodes;
import com.skyweednet.weedlook.models.Tasting;
import com.skyweednet.weedlook.views.main.SamplesActivity;

import static com.facebook.FacebookSdk.getApplicationContext;




public class TastingsListFragment extends Fragment implements TastingsListener {

    public static final String SAMPLE = "com.skyweednet.weedlook.views.mytastings.KEY.SAMPLE";
    public static final String TASTING = "com.skyweednet.weedlook.views.mytastings.KEY.TASTING";


    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private TastingsAdapter adapter;


    public TastingsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tastings_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Actualizando... Espere por favor");
        progressDialog.show();

        recyclerView = (RecyclerView) view.findViewById(R.id.tastingsRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final CurrentUser currentUser = new CurrentUser();
        String email = new EmailProcessor().sanitizedEmail(currentUser.email());

        adapter = new TastingsAdapter(this,email);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void delete(final Tasting tasting) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage("¿Desea eliminar esta cata de la lista?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Sí",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Toast.makeText(getApplicationContext(),"Se ha eliminado la cata con éxito", Toast.LENGTH_SHORT).show();
                        CurrentUser currentUser = new CurrentUser();
                        String email = new EmailProcessor().sanitizedEmail(currentUser.email());

                        new Nodes().tasting(email).child(tasting.getKey()).removeValue();
                        //new Nodes().scorebysamplekey(email,tasting.getSampleKey(),tasting.getScoreKey()).removeValue();
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
    public void dataChanged() {

        progressDialog.dismiss();

    }

    @Override
    public void add() {
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);
    }

    @Override
    public void tasting(Tasting tasting) {

        Intent intent = new Intent(getActivity(), SamplesActivity.class);
        intent.putExtra("SAMPLE", tasting);
        intent.putExtra("TASTING", tasting);// pass your values and retrieve them in the other Activity using keyName
        startActivity(intent);



    }





}


