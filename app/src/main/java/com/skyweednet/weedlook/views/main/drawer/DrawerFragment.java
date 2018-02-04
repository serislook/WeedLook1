package com.skyweednet.weedlook.views.main.drawer;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.skyweednet.weedlook.R;
import com.skyweednet.weedlook.data.CurrentUser;
import com.skyweednet.weedlook.views.login.LoginActivity;
import com.skyweednet.weedlook.views.main.SharedSamplesActivity;
import com.skyweednet.weedlook.views.mytastings.MyTastingsActivity;
import com.skyweednet.weedlook.views.tasterguide.TasterGuideActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrawerFragment extends Fragment {


    private DrawerLayout drawer;

    public DrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drawer, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView logout = (TextView) view.findViewById(R.id.logoutTv);
        Button mytastingsBtn = (Button) view.findViewById(R.id.mytastingsBtn);
        Button studiesBtn = (Button) view.findViewById(R.id.studiesBtn);
        Button guideBtn = (Button) view.findViewById(R.id.guideBtn);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(getActivity())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                                getActivity().finish();


                            }
                        });
            }
        });

        TextView userEmail = (TextView) view.findViewById(R.id.emailTv);
        userEmail.setText(new CurrentUser().email());

        mytastingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
                drawer.closeDrawers();
                
                Intent intent = new Intent(getActivity(), MyTastingsActivity.class);
                startActivity(intent);
            }
        });

        studiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
                drawer.closeDrawers();

                Intent intent = new Intent(getActivity(), SharedSamplesActivity.class);
                startActivity(intent);
            }
        });

        guideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
                drawer.closeDrawers();

                Intent intent = new Intent(getActivity(), TasterGuideActivity.class);
                startActivity(intent);
            }
        });

    }
}
