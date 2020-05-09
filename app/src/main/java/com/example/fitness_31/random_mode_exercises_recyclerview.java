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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class random_mode_exercises_recyclerview extends AppCompatActivity {




    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private Button start_exercises;

    public ArrayList<exerciseItem> randomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_mode_exercises_recyclerview);

        final Intent intent = getIntent();
        final String reps=intent.getStringExtra("repetation");
        start_exercises = findViewById(R.id.start_button);

        //Adding up the exercise images

        ArrayList<exerciseItem> exerciseList = new ArrayList<>();
        exerciseList.add(new exerciseItem(R.drawable.ex1,"Pushup",reps,false,2));
        exerciseList.add(new exerciseItem(R.drawable.ex2,"crunches",reps,false,2.5));
        exerciseList.add(new exerciseItem(R.drawable.ex3,"Toe Touches",reps,false,1.8));
        exerciseList.add(new exerciseItem(R.drawable.ex4,"stretching",reps,false,1.5));
        exerciseList.add(new exerciseItem(R.drawable.ex5,"Leg Raise",reps,false,1.2));
        exerciseList.add(new exerciseItem(R.drawable.ex6,"Dumbbell Pushups",reps,false,4.0));
        exerciseList.add(new exerciseItem(R.drawable.ex7,"Bicep Curls",reps,false,2.5));
        exerciseList.add(new exerciseItem(R.drawable.ex8,"decline Pushups",reps,false,2.2));
        exerciseList.add(new exerciseItem(R.drawable.ex9,"One Leg Stand",reps,false,2));
        exerciseList.add(new exerciseItem(R.drawable.ex10,"Cable Row",reps,false,2.8));
        exerciseList.add(new exerciseItem(R.drawable.ex11,"Incline Bench Press",reps,false,3.2));
        exerciseList.add(new exerciseItem(R.drawable.ex12,"Bench Press",reps,false,3));
        exerciseList.add(new exerciseItem(R.drawable.ex13,"Hammer Strength Machine",reps,false,3.0));
        exerciseList.add(new exerciseItem(R.drawable.ex14,"Inclined Crunches",reps,false,2.5));

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




        recyclerView = findViewById(R.id.randomExercise_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Setting up the adapter
        recyclerViewAdapter = new RecyclerViewAdapter_exerciseRandom(randomList);


        //Using adapter to fill data into the recycler view
        recyclerView.setAdapter(recyclerViewAdapter);

        start_exercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(random_mode_exercises_recyclerview.this,workout_timer.class);
                intent2.putExtra("duration_val", Integer.parseInt(reps));
                Bundle bundle = new Bundle();
                bundle.putSerializable("randomExerciseList",randomList);
                intent2.putExtra("randomExercise_bundle",bundle);
                startActivity(intent2);
            }
        });

    }
}
