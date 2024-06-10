package com.example.moneyquest.activities;

import static android.content.ContentValues.TAG;
//import static com.example.moneyquest.model.Safe.getSafesData;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import com.example.moneyquest.R;
import com.example.moneyquest.adapter.QuestAdapter;
import com.example.moneyquest.adapter.RecyclerViewInterface;
import com.example.moneyquest.adapter.SafesAdapter;
import com.example.moneyquest.model.Child;
import com.example.moneyquest.model.Quest;
import com.example.moneyquest.model.Safe;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreasureActivity extends AppCompatActivity implements RecyclerViewInterface {

    private final int REQUEST_QUEST = 001;
    private final int REQUEST_SAFE = 011;
    private final int QUEST_SEND_CODE = 10;
    private final int SAFE_DEPOSIT_CODE = 10;
    private DatabaseReference mDatabase;
    private DatabaseReference childsRef;

    String childId;

    private Child child;

    private TextView treasure;
    private QuestAdapter questAdapter;
    private SafesAdapter safesAdapter;
    private DecimalFormat formato = new DecimalFormat("#.##");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treasure);

        treasure = findViewById(R.id.txtTreasure);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            childId = extras.getString("child_id");
            Log.d(TAG, "Child ID: " + childId);
            Log.d("firebase", mDatabase.toString());
            verifyUser(childId);
        }

        System.out.println("teste");

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

        setTreasure(child.getBalance());
    }

    private void getUserData(HashMap<String, Object> hashChild) {
        child = Child.fromHashMap(hashChild);
        Log.d("firebase", "Child map: " + child.getBalance());

        setTreasure(child.getBalance());

        setSafesRecycler();
        setQuestsRecycler();
    }

    private void setQuestsRecycler() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        questAdapter = new QuestAdapter(this, child.getQuests(), this);
        recyclerView.setAdapter(questAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setSafesRecycler() {
        RecyclerView recyclerView = findViewById(R.id.recyclerSafes);
        safesAdapter = new SafesAdapter(this, child.getSafes(), this);
        recyclerView.setAdapter(safesAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setTreasure(Double balance) {
        treasure.setText("R$ " + formato.format(balance));
    }

    @Override
    public void onQuestClick(int position) {

        List<Quest> quests = child.getQuests();
        Intent i = new Intent(getApplicationContext(), OpenQuestActivity.class);
        i.putExtra("quest_position", String.valueOf(position));
        i.putExtra("quest_name", quests.get(position).getTitle());
        i.putExtra("quest_desc", quests.get(position).getDescription());
        i.putExtra("quest_reward", quests.get(position).getReward().toString());
        i.putExtra("quest_img", quests.get(position).getImage());

        i.putExtra("child_id", childId);
        i.putExtra("child_balance", child.getBalance().toString());
        startActivityForResult(i, REQUEST_QUEST);
    }

    @Override
    public void onSafeClick(int position) {

        List<Safe> safes = child.getSafes();
        Intent i = new Intent(getApplicationContext(), OpenSafeActivity.class);

        i.putExtra("goal", safes.get(position).getGoal());
        i.putExtra("progress", safes.get(position).getBalance());
        i.putExtra("color", safes.get(position).getColor());
        i.putExtra("safeName", safes.get(position).getName());
        i.putExtra("child_id", childId);
        i.putExtra("child_balance", child.getBalance().toString());
        i.putExtra("safePos", String.valueOf(position+1));

        startActivityForResult(i, REQUEST_SAFE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_QUEST && resultCode == QUEST_SEND_CODE) {
            Double value = Double.valueOf(data.getStringExtra("value"));
            String title = data.getStringExtra("title");
            int position = data.getIntExtra("position", 0);

            child.updateBalance(value);
            setTreasure(child.getBalance());
            mDatabase.child("childs").child(childId).child("balance").setValue(child.getBalance());

            child.removeQuestByTitle(title);
            questAdapter.notifyItemRemoved(position);
        }

        if (requestCode == REQUEST_SAFE && resultCode == SAFE_DEPOSIT_CODE) {
            int position = data.getIntExtra("position", 0);
            int progress = data.getIntExtra("progress", 0);
            child.getSafes().get(position).setBalance(progress);

            Double balance = data.getDoubleExtra("balance", 0.0);
            child.setBalance(balance);
            setTreasure(child.getBalance());
            mDatabase.child("childs").child(childId).child("balance").setValue(child.getBalance());

            safesAdapter.notifyItemChanged(position);
        }
    }

}