package com.example.moneyquest.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.moneyquest.R;
import com.example.moneyquest.RequestCode;
import com.example.moneyquest.adapter.RecyclerViewInterface;
import com.example.moneyquest.adapter.SafesAdapter;
import com.example.moneyquest.model.Child;
import com.example.moneyquest.model.Safe;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SafesActivity extends AppCompatActivity implements RecyclerViewInterface {

    private DatabaseReference mDatabase;

    private Child child;
    private String childId;
    private SafesAdapter safesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safes);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        child = (Child) getIntent().getSerializableExtra("child");
        childId = getIntent().getStringExtra("childId");

        setSafesRecycler();
    }

    private void setSafesRecycler() {
        RecyclerView recyclerView = findViewById(R.id.safesRecycler);
        safesAdapter = new SafesAdapter(this, child.getSafes(), this, R.layout.safes);
        recyclerView.setAdapter(safesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onQuestClick(int position) {}

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

        startActivityForResult(i, RequestCode.REQUEST_SAFE.getCode());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestCode.REQUEST_SAFE.getCode() && resultCode == RequestCode.SAFE_DEPOSIT_CODE.getCode()) {
            int position = data.getIntExtra("position", 0);
            int progress = data.getIntExtra("progress", 0);
            child.getSafes().get(position).setBalance(progress);

            Double balance = data.getDoubleExtra("balance", 0.0);
            child.setBalance(balance);
            mDatabase.child("childs").child(childId).child("balance").setValue(child.getBalance());

            safesAdapter.notifyItemChanged(position);
        }
    }

    public void treasureScreen(View v) {
        setResult(RequestCode.SAFE_SCREEN_CODE.getCode(),
                new Intent()
                        .putExtra("child", child));
        finish();
    }

    public void questsScreen(View v) {
        setResult(RequestCode.GOTO_QUESTS.getCode(),
                new Intent()
                        .putExtra("child", child));
        finish();
    }
}