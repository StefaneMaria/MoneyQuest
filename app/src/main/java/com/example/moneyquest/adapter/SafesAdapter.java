package com.example.moneyquest.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneyquest.R;
import com.example.moneyquest.model.Safe;

import java.util.List;

public class SafesAdapter extends RecyclerView.Adapter<SafesAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    List<Safe> safeList;

    public SafesAdapter(Context context, List<Safe> safeList, RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.safeList = safeList;
    }

    @NonNull
    @Override
    public SafesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.safes_treasure, parent, false);

        return new SafesAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.pigImage.setImageResource(R.drawable.pig);
        holder.progressBar.setMax(safeList.get(position).getGoal());
        holder.progressBar.setProgress(safeList.get(position).getBalance());
        holder.progressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor(safeList.get(position).getColor())));
    }

    @Override
    public int getItemCount() {
        return safeList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView pigImage;
        ProgressBar progressBar;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            pigImage = itemView.findViewById(R.id.imgPig);
            progressBar = itemView.findViewById(R.id.progressBar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onSafeClick(pos);
                        }
                    }
                }
            });
        }
    }
}
