package com.example.moneyquest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.moneyquest.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OpenSafeActivity extends AppCompatActivity {

    private final int SAFE_DEPOSIT_CODE = 10;
    private final int SAFE_CLOSE_CODE = 11;
    ProgressBar progressBar;
    TextView safeName, value, balance;

    Boolean hasDeposit = false;
    String safePos, childId;
    Integer goal, progress;
    Integer count = 0;
    Double balanceValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_safe);

        progressBar = findViewById(R.id.progress);
        safeName = findViewById(R.id.textSafeName);
        value = findViewById(R.id.textDeposit);
        balance = findViewById(R.id.textBalance);

        progressBar.setMax(getIntent().getIntExtra("goal", 0));
        progressBar.setProgress(getIntent().getIntExtra("progress", 0));
        progressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor(getIntent().getStringExtra("color"))));

        safeName.setText(getIntent().getStringExtra("safeName"));
        balance.setText(getIntent().getStringExtra("child_balance"));

        goal = getIntent().getIntExtra("goal", 0);
        progress = getIntent().getIntExtra("progress", 0);
        balanceValue = Double.parseDouble(getIntent().getStringExtra("child_balance"));
        safePos = getIntent().getStringExtra("safePos");
        childId = getIntent().getStringExtra("child_id");
        value.setText("00.00");
    }

    public void plus (View v) {
        if ((count+1) < goal && (count+1) < balanceValue && (count+1) + progress <= goal) count += 1;
        value.setText(count.toString());
    }

    public void minus (View v) {
        if(count > 0) count -= 1;
        value.setText(count.toString());
    }

    public void deposit (View v) {
        progress += count;
        progressBar.setProgress(progress);

        balanceValue -= count;

        balance.setText(balanceValue.toString());
        hasDeposit = true;

    }

    public void close (View v) {
        if(hasDeposit) {

            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("childs").child(childId).child("safes").child(safePos).child("balance").setValue(progress);

            setResult(SAFE_DEPOSIT_CODE,
                    new Intent()
                            .putExtra("balance", balanceValue)
                            .putExtra("progress", progress)
                            .putExtra("position", safePos));
        }
        finish();
    }
}