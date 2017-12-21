package com.skyweednet.weedlook.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.skyweednet.weedlook.R;
import com.skyweednet.weedlook.data.CurrentUser;
import com.skyweednet.weedlook.data.EmailProcessor;
import com.skyweednet.weedlook.data.Nodes;
import com.skyweednet.weedlook.models.Users;
import com.skyweednet.weedlook.views.add.AddSampleActivity;
import com.skyweednet.weedlook.views.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        validasesion();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //String[] samplesTypes = getResources().getStringArray(R.array.samples);
                startActivity(new Intent(MainActivity.this, AddSampleActivity.class));
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

    }

    private void validasesion() {
        final CurrentUser currentUser = new CurrentUser();

        if (currentUser.getCurrentUser() != null) {

            Users user = new Users();
            user.setEmail(currentUser.email());
            user.setName(currentUser.getCurrentUser().getDisplayName());
            user.setUid(currentUser.uid());
            String key = new EmailProcessor().sanitizedEmail(currentUser.email());
            new Nodes().user(key).setValue(user);

        } else {

            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}