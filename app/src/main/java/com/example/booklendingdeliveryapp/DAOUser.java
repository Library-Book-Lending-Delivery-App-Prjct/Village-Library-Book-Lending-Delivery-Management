package com.example.booklendingdeliveryapp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOUser {

    private DatabaseReference databaseReference;
    public DAOUser(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(UserHelperClass.class.getSimpleName());
    }

    public Task<Void> add(UserHelperClass user){
        return databaseReference.push().setValue(user);
    }

}
