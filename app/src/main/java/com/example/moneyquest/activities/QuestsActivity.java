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
import com.example.moneyquest.adapter.QuestAdapter;
import com.example.moneyquest.adapter.RecyclerViewInterface;
import com.example.moneyquest.model.Child;
import com.example.moneyquest.model.Quest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class QuestsActivity extends AppCompatActivity implements RecyclerViewInterface {

    private DatabaseReference mDatabase;
    private QuestAdapter questAdapter;
    private Child child;
    private String childId;
    private Integer positionRemoved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quests);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        child = (Child) getIntent().getSerializableExtra("child");
        childId = getIntent().getStringExtra("childId");

        setQuestsRecycler();
    }

    private void setQuestsRecycler() {
        RecyclerView recyclerView = findViewById(R.id.questsRecycler);
        questAdapter = new QuestAdapter(this, child.getQuests(), this);
        recyclerView.setAdapter(questAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        startActivityForResult(i, RequestCode.REQUEST_QUEST.getCode());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestCode.REQUEST_QUEST.getCode() && resultCode == RequestCode.QUEST_SEND_CODE.getCode()) {
            Double value = Double.valueOf(data.getStringExtra("value"));
            String title = data.getStringExtra("title");
            positionRemoved = data.getIntExtra("position", 0);

            child.updateBalance(value);
            mDatabase.child("childs").child(childId).child("balance").setValue(child.getBalance());

            child.removeQuestByTitle(title);
            questAdapter.notifyItemRemoved(positionRemoved);
        }
    }

    @Override
    public void onSafeClick(int position) {
    }

    public void treasureScreen(View v) {
        setResult(RequestCode.QUEST_SCREEN_CODE.getCode(),
                new Intent()
                        .putExtra("child", child)
                        .putExtra("positionRemoved", positionRemoved));
        finish();
    }

    public void safesScreen(View v) {
        setResult(RequestCode.GOTO_SAFES.getCode(),
                new Intent()
                        .putExtra("child", child)
                        .putExtra("positionRemoved", positionRemoved));
        finish();
    }
}