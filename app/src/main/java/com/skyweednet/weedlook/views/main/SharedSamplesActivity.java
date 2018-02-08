package com.skyweednet.weedlook.views.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.skyweednet.weedlook.R;
import com.skyweednet.weedlook.adapters.SharedSamplesAdapter;
import com.skyweednet.weedlook.adapters.SharedSamplesListener;
import com.skyweednet.weedlook.data.CurrentUser;
import com.skyweednet.weedlook.data.EmailProcessor;
import com.skyweednet.weedlook.models.Sample;
import com.skyweednet.weedlook.views.tastings.TastingsActivity;

public class SharedSamplesActivity extends AppCompatActivity implements SharedSamplesListener {

    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private SharedSamplesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_samples);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Actualizando... Espere por favor");
        progressDialog.show();

        recyclerView = (RecyclerView) findViewById(R.id.sharedsamplesRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final CurrentUser currentUser = new CurrentUser();
        String email = new EmailProcessor().sanitizedEmail(currentUser.email());

        adapter = new SharedSamplesAdapter(this, email);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void tasting(Sample sample) {
        Intent intent = new Intent(this, TastingsActivity.class);
        intent.putExtra("SAMPLE_KEY", sample);
        intent.putExtra("COMPARTIDA", true);
        startActivity(intent);
        finish();
    }

    @Override
    public void dataChanged() {
        progressDialog.dismiss();
    }

    @Override
    public void add() {
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);
    }
}
