package com.example.moneyquest.activities;

import static com.example.moneyquest.model.Safe.getSafesData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.moneyquest.R;
import com.example.moneyquest.SafeAdapter;
import com.example.moneyquest.model.Safe;

import java.util.List;

public class TreasureActivity extends AppCompatActivity {
    List<Safe> safes = getSafesData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treasure);

//        RecyclerView safesRecyclerView = findViewById(R.id.safesRecyclerView);
//        SafeAdapter adapter = new SafeAdapter(safes);
//        safesRecyclerView.setAdapter(adapter);
    }

}