package com.skyweednet.weedlook.views.finder;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.skyweednet.weedlook.data.CurrentUser;
import com.skyweednet.weedlook.data.EmailProcessor;
import com.skyweednet.weedlook.data.Nodes;
import com.skyweednet.weedlook.models.Sample;
import com.skyweednet.weedlook.models.Users;

/**
 * Created by osx on 23-12-17.
 */

public class UserValidation {

    private FinderCallback callback;
    private Context context;

    public UserValidation(FinderCallback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    public void init(String email, Sample sample) {
        if (email.trim().length() > 0) {
            if (email.contains("@")) {
                String currentEmail = new CurrentUser().email();

                if (!email.equals(currentEmail)) {
                    findUser(email, sample);
                } else {
                    callback.error("¿Cata contigo mismo?");

                }

            } else {
                callback.error("Email mal escrito");

            }

        } else {
            callback.error("Se necesita email");

        }


    }

    private void findUser(final String email, final Sample sample) {
        new Nodes().user(new EmailProcessor().sanitizedEmail(email)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users otherUser = dataSnapshot.getValue(Users.class);

                if (otherUser != null) {
                    new Nodes().sharedSamples().child(new EmailProcessor().sanitizedEmail(email)).child(sample.getKey()).setValue(sample);
                    callback.success();
                } else {
                    callback.notFound();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}
