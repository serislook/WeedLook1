package com.skyweednet.weedlook.views.sample;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skyweednet.weedlook.data.CurrentUser;
import com.skyweednet.weedlook.data.EmailProcessor;
import com.skyweednet.weedlook.models.Tasting;

/**
 * Created by osx on 06-01-18.
 */

public class OverallCalculation {

    public void average(String keySample) {
        //TODO fix this in the nodes class
        DatabaseReference tastings = FirebaseDatabase.getInstance().getReference().child("tastings").child(new EmailProcessor().sanitizedEmail(new CurrentUser().email())).child(keySample);
        tastings.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double sum = 0;
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Tasting tasting = childSnapshot.getValue(Tasting.class);
                    sum =+ tasting.getAverage();

                }
                long size = dataSnapshot.getChildrenCount();
                double average = sum/size;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
