package com.example.moneyquest.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneyquest.R;
import com.example.moneyquest.model.Quest;

import java.util.ArrayList;
import java.util.List;

public class QuestAdapter extends RecyclerView.Adapter<QuestAdapter.MyViewHolder> {

    Context context;
    List<Quest> questList;

    public QuestAdapter(Context context, List<Quest> questList) {
        this.context = context;
        this.questList = questList;
    }

    @NonNull
    @Override
    public QuestAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.quests, parent, false);

        return new QuestAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestAdapter.MyViewHolder holder, int position) {

        holder.titleText.setText(questList.get(position).getTitle());
        holder.iconImage.setImageResource(R.drawable.pig);
        holder.plankImage.setImageResource(R.drawable.madeira_missao);

    }

    @Override
    public int getItemCount() {
        return questList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView plankImage;
        ImageView iconImage;
        TextView titleText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            plankImage = itemView.findViewById(R.id.imgPlank);
            iconImage = itemView.findViewById(R.id.imgMission);
            titleText = itemView.findViewById(R.id.textTitle);

        }
    }
}
