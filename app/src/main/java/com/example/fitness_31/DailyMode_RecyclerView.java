package com.example.fitness_31;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fitness_31.adapter.RecyclerViewAdapter_exerciseRandom;
import com.example.fitness_31.model.exerciseItem;

import java.util.ArrayList;
import java.util.Random;

public class DailyMode_RecyclerView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private Button start_exercises;

    final String reps="15";

    public ArrayList<exerciseItem> randomList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_mode_exercises_recyclerview);

        final Intent intent = getIntent();
        final String reps="15";
        //TODO: remove this repetation as the value will be set by deafult
        start_exercises = findViewById(R.id.start_button);

    selectExercises();

    recyclerViewAdapterData();

    start_exercises.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sendBundledIntent();
        }
    });


    }



    private void selectExercises() {
        //Adding up the exercise images

        ArrayList<exerciseItem> exerciseList = new ArrayList<>();

        exerciseList.add(new exerciseItem(R.drawable.ex1,"Pushup","15",false,2));
        exerciseList.add(new exerciseItem(R.drawable.ex2,"crunches","15",false,2.5));
        exerciseList.add(new exerciseItem(R.drawable.ex3,"Toe Touches","15",false,1.8));
        exerciseList.add(new exerciseItem(R.drawable.ex4,"stretching","15",false,1.5));
        exerciseList.add(new exerciseItem(R.drawable.ex5,"Leg Raise","15",false,1.2));
        exerciseList.add(new exerciseItem(R.drawable.ex6,"Dumbell Pushups","15",false,4.0));
        exerciseList.add(new exerciseItem(R.drawable.ex7,"Bicep Curls","15",false,2.5));
        exerciseList.add(new exerciseItem(R.drawable.ex8,"decline Pushups","15",false,2.2));
        exerciseList.add(new exerciseItem(R.drawable.ex9,"One Leg Stand","15",false,2));
        exerciseList.add(new exerciseItem(R.drawable.ex10,"Cable Row","15",false,2.8));
        exerciseList.add(new exerciseItem(R.drawable.ex11,"Incline Bench Press","15",false,3.2));
        exerciseList.add(new exerciseItem(R.drawable.ex12,"Bench Press","15",false,3));
        exerciseList.add(new exerciseItem(R.drawable.ex13,"Hammer Strength Machine","15",false,3.0));
        exerciseList.add(new exerciseItem(R.drawable.ex14,"Inclined Crunches","15",false,2.5));

        int n;
        randomList = new ArrayList<>();
        for(int  i=0;i<5;i++){
            Random random = new Random();
            n=random.nextInt(14);
            if(!randomList.contains(exerciseList.get(n)))
                randomList.add(exerciseList.get(n));
            else
                i--;
        }

    }
    private void recyclerViewAdapterData() {
        recyclerView = findViewById(R.id.randomExercise_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Setting up the adapter
        recyclerViewAdapter = new RecyclerViewAdapter_exerciseRandom(randomList);


        //Using adapter to fill data into the recycler view
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void sendBundledIntent() {
        Intent intent2 = new Intent(DailyMode_RecyclerView.this,workout_timer2.class);
        intent2.putExtra("duration_val", Integer.parseInt(reps));
        Bundle bundle = new Bundle();
        bundle.putSerializable("randomExerciseList",randomList);
        intent2.putExtra("randomExercise_bundle",bundle);
        startActivity(intent2);
    }
}
