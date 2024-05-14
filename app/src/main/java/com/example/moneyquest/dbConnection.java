package com.example.moneyquest;

import com.example.moneyquest.model.Child;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dbConnection {
    private DatabaseReference mDatabase;

    public dbConnection() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void writeNewUser(String childId, String name, String email) {
        Child child = new Child(name, email);

        mDatabase.child("childs").child(childId).setValue(child);

    }

}
