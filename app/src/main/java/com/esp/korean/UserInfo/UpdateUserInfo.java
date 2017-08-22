package com.esp.korean.UserInfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.esp.korean.Login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateUserInfo extends AsyncTask<Void, Void, Void> {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    private DatabaseReference userRef;
    private UserInfo userInfo;
    private String userId;

    public UpdateUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        userId = mUser.getUid();
        database = FirebaseDatabase.getInstance();
        dbReference = database.getReference("users");
    }

    @Override
    protected Void doInBackground(Void... params) {
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(userId)) {
                    userRef = dbReference.child(userId);
                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            userInfo = dataSnapshot.getValue(UserInfo.class);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d("DB", "The read failed: " + databaseError.getMessage());
                        }
                    });
                } else {
                    userInfo = new UserInfo(mUser.getDisplayName(), mUser.getPhotoUrl().toString());
                    userRef = dbReference.child(userId);
                    userRef.setValue(userInfo);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return null;
    }
}
