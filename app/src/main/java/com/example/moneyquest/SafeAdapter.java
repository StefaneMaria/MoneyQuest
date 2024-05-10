package com.example.moneyquest;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneyquest.model.Safe;

import java.util.List;

public class SafeAdapter extends RecyclerView.Adapter<SafeAdapter.SafeViewHolder> {

    private List<Safe> safes;

    public SafeAdapter(List<Safe> safes) {
        this.safes = safes;
    }

    @Override
    public SafeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.safe_item, parent, false);
        return new SafeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SafeViewHolder holder, int position) {
        Safe safe = safes.get(position);
        holder.imageView.setImageDrawable(Drawable.createFromPath(safe.getImage()));
        holder.progressBar.setProgress(safe.getBalance());
        holder.progressBar.setMax(safe.getGoal());
    }

    @Override
    public int getItemCount() {
        return safes.size();
    }

    static class SafeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;

        public SafeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.safeImage);
            progressBar = itemView.findViewById(R.id.safeProgress);
        }
    }
}

