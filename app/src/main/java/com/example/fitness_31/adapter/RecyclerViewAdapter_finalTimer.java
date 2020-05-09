package com.example.fitness_31.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.fitness_31.R;
import com.example.fitness_31.model.exerciseItem;

import java.util.ArrayList;

public class RecyclerViewAdapter_finalTimer extends RecyclerView.Adapter<RecyclerViewAdapter_finalTimer.ViewHolder> {
    private ArrayList<exerciseItem> mExerciseList;

    public static  class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView exerciseImageView;
        public ImageView done;
        public TextView exerciseNameTextview;
//        public TextView repetationTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseImageView = itemView.findViewById(R.id.final_image);
            exerciseNameTextview =itemView.findViewById(R.id.final_name);
            done = itemView.findViewById(R.id.tick);
//            repetationTextView = itemView.findViewById(R.id.repetation);
        }
    }

    public RecyclerViewAdapter_finalTimer(ArrayList<exerciseItem> exerciseItems){
        this.mExerciseList=exerciseItems;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.final_workout_rows,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        exerciseItem currentItem = mExerciseList.get(position);

        holder.exerciseImageView.setImageResource(currentItem.getmImageResource());
        holder.exerciseNameTextview.setText(currentItem.getName());
//        holder.repetationTextView.setText("Duration : "+currentItem.getmText2()+" min");

//
        checkProgress(holder,currentItem);

    }

    private void checkProgress(ViewHolder holder, exerciseItem current) {

        if(current.getDone())
            holder.done.setImageResource(R.drawable.tick);
        else
            holder.done.setImageResource(R.drawable.grey_tick);
    }


    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }
}
