package com.example.moneyquest.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Debug;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moneyquest.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class OpenQuestActivity extends AppCompatActivity {

    private final int GALLERY_REQUEST_CODE = 1000;
    private final int QUEST_SEND_CODE = 10;
    private final int QUEST_CLOSE_CODE = 11;
    ImageView imwQuestImage, imwQuestProve;
    TextView txvQuestName, txvQuestDesc, txvQuestReward, txvChildBalance;

    String childId, quest_position, questReward, questTitle;
    Bitmap imageToSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_quest);

        imwQuestImage = findViewById(R.id.questImg);
        imwQuestProve = findViewById(R.id.questProveImg);
        txvQuestName = findViewById(R.id.textQuestName);
        txvQuestDesc = findViewById(R.id.textQuestDesc);
        txvQuestReward = findViewById(R.id.textQuestValue);
        txvChildBalance = findViewById(R.id.textBalance);

        questTitle = getIntent().getStringExtra("quest_name");
        String questDesc = getIntent().getStringExtra("quest_desc");
        questReward = getIntent().getStringExtra("quest_reward");
        String childBalance = getIntent().getStringExtra("child_balance");
        childId = getIntent().getStringExtra("child_id");
        quest_position = getIntent().getStringExtra("quest_position");

        txvQuestName.setText(questTitle);
        txvQuestDesc.setText(questDesc);
        txvQuestReward.setText(questReward);
        txvChildBalance.setText(childBalance);
        imwQuestImage.setImageResource(R.drawable.pig);
    }

    public void pickImage(View v) {
        Intent iGallery = new Intent(Intent.ACTION_PICK);
        iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(iGallery, GALLERY_REQUEST_CODE);
    }
    public void close(View v){
        setResult(QUEST_CLOSE_CODE);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( resultCode == RESULT_OK && requestCode == GALLERY_REQUEST_CODE) {
            imwQuestProve.setImageURI(data.getData());
            try {
                imageToSave = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendQuest(View v) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if(imageToSave == null) return;

        imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("childs").child(childId).child("quests").child(quest_position).child("image").setValue(data.toString());

        setResult(QUEST_SEND_CODE,
                new Intent()
                        .putExtra("value", questReward)
                        .putExtra("title", questTitle)
                        .putExtra("position", quest_position));
        finish();
    }
}