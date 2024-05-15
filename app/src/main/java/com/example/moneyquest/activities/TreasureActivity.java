package com.example.moneyquest.activities;

import static android.content.ContentValues.TAG;
//import static com.example.moneyquest.model.Safe.getSafesData;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.moneyquest.R;
import com.example.moneyquest.SafeAdapter;
import com.example.moneyquest.model.Child;
import com.example.moneyquest.model.Safe;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreasureActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private DatabaseReference childsRef;

    private Child child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treasure);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String childId = extras.getString("child_id");
            Log.d(TAG, "Child ID: " + childId);
            Log.d("firebase", mDatabase.toString());
            verifyUser(childId);
        }
    }

    private void verifyUser(String childId) {
        mDatabase.child("childs").child(childId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", "User Found");
                    getOrCreateUser(task.getResult().getValue(), childId);
                }
            }
        });
    }

    private void getOrCreateUser(Object data, String childId) {
        if(data == null)
            createNewUser(childId);
        else
            getUserData((HashMap<String, Object>) data);
    }

    private void createNewUser(String childId) {
        Map<String, Object> users = new HashMap<>();
        child = new Child();
        users.put(childId, child);

        childsRef = mDatabase.child("childs");
        childsRef.updateChildren(users);
    }

    private void getUserData(HashMap<String, Object> hashChild) {
        child = Child.fromHashMap(hashChild);
        Log.d("firebase", "Child map: " + child.getBalance());
    }

}