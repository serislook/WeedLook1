package com.skyweednet.weedlook.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by osx on 22-11-17.
 */

public class Nodes {

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();

    public DatabaseReference users() {
        return root.child("users");
    }

    public DatabaseReference user(String key) {
        return users().child(key);
    }

    public DatabaseReference samples() {
        return root.child("samples");
    }

    public DatabaseReference sample(String key) {
        return samples().child(key);
    }

    public DatabaseReference samplebyemail(String email) {
        return samples().child(email);
    }
}
