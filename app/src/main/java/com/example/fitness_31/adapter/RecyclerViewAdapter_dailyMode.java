package com.example.fitness_31.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter_dailyMode extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

//TODO: use this adapter to populate the data in the daily mode.
// PS : Try to avoid this adapter all together and use the random mode recycler view adapter .
// PS: Also try to use the finalTimer recycler view for daily mode as well.