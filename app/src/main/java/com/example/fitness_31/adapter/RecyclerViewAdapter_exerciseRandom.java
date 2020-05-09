package com.example.fitness_31.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitness_31.R;
import com.example.fitness_31.model.exerciseItem;

import java.util.ArrayList;

public class RecyclerViewAdapter_exerciseRandom extends RecyclerView.Adapter<RecyclerViewAdapter_exerciseRandom.ViewHolder> {
    private ArrayList<exerciseItem> mExerciseList;

public static  class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView exerciseImageView;
    public TextView exerciseNameTextview;
    public TextView repetationTextView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        exerciseImageView = itemView.findViewById(R.id.exercise_image);
        exerciseNameTextview =itemView.findViewById(R.id.exercise_name);
        repetationTextView = itemView.findViewById(R.id.repetation);
    }
}

    public RecyclerViewAdapter_exerciseRandom(ArrayList<exerciseItem> exerciseItems){
    this.mExerciseList=exerciseItems;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.exercise_rows,parent,false);
    ViewHolder vh = new ViewHolder(view);
    return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    exerciseItem currentItem = mExerciseList.get(position);

    holder.exerciseImageView.setImageResource(currentItem.getmImageResource());
    holder.exerciseNameTextview.setText(currentItem.getName());
    holder.repetationTextView.setText("Duration : "+currentItem.getRepetationsSelected()+" Secs");

    }

    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }
}
