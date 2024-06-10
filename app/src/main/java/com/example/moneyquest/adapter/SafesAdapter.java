package com.example.moneyquest.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
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
//    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    List<Safe> safeList;

    public SafesAdapter(Context context, List<Safe> safeList) {
//        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.safeList = safeList;
    }

    @NonNull
    @Override
    public SafesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.safes_treasure, parent, false);

        return new SafesAdapter.MyViewHolder(view);
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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            pigImage = itemView.findViewById(R.id.imgPig);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
