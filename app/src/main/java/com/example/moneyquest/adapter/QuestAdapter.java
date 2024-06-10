package com.example.moneyquest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneyquest.R;
import com.example.moneyquest.model.Quest;

import java.util.List;

public class QuestAdapter extends RecyclerView.Adapter<QuestAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    List<Quest> questList;

    public QuestAdapter(Context context, List<Quest> questList, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.questList = questList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public QuestAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.quests, parent, false);

        return new QuestAdapter.MyViewHolder(view, recyclerViewInterface);
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

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            plankImage = itemView.findViewById(R.id.imgPlank);
            iconImage = itemView.findViewById(R.id.imgPig);
            titleText = itemView.findViewById(R.id.textTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onQuestClick(pos);
                        }
                    }
                }
            });

        }
    }
}
