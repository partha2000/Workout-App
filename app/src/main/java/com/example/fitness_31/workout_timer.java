package com.example.fitness_31;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.fitness_31.adapter.RecyclerViewAdapter_finalTimer;
import com.example.fitness_31.model.exerciseItem;
import com.example.fitness_31.model.globalTracker;

import java.util.ArrayList;

public class workout_timer extends AppCompatActivity {

    public MediaPlayer player;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private Boolean isWorkoutCompleted;
    private ArrayList<exerciseItem> fiveExercisesPassed;
    public int i;
    public globalTracker today;

//    private LottieAnimationView animationView;

    private long start_time_in_millis;
    private long time_left_in_millis;
    private int time_in_sec;

    private Boolean isTimerRunning = true;
    private ProgressBar progressBarTimer;
    private CountDownTimer countDownTimer;
    private TextView clock;
    private ImageView counterCard;
//    private Button skip_button;
//    private TextView rest_time;


    public AlertDialog.Builder builder,builder1;
    public AlertDialog dialog,dialog1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_timer);

        progressBarTimer = findViewById(R.id.workout_timer);
        clock = findViewById(R.id.clock);
        counterCard = findViewById(R.id.info_imageView);
//        skip_button = findViewById(R.id.skip_rest);
//        rest_time = findViewById(R.id.rest_timer);

//        animationView = findViewById(R.id.hourglass_animation);
//        animationView.setVisibility(View.GONE);

        Intent intent3 = getIntent();
        time_in_sec = intent3.getIntExtra("duration_val", 6);
        Log.d("Time in sec", String.valueOf(time_in_sec));
        Bundle bundle = intent3.getBundleExtra("randomExercise_bundle");
        fiveExercisesPassed = (ArrayList<exerciseItem>) bundle.getSerializable("randomExerciseList");


        today = new globalTracker(false, time_in_sec);
//        today.setTime(time_in_sec);
//        today.setToday();


        // this is  done for the first element
        i = 0;

        start_time_in_millis = (long) (time_in_sec * 1000 * fiveExercisesPassed.get(i).getTimePerRepinSec());
        time_left_in_millis = start_time_in_millis;
        progressBarTimer.setMax((int) start_time_in_millis / 1000);
        Log.d("Timer running", " true");
        Log.d("Duration", String.valueOf(start_time_in_millis));


        counterCard.setImageResource(fiveExercisesPassed.get(i).getmImageResource());
        startTimer();
        fiveExercisesPassed.get(i).setDone(true);
        playMedia(i);

//        for(int i =1;i<5;i++) {
//
//            if (!isTimerRunning) {
//                startTimer();
//                counterCard.setImageResource(fiveExercisesPassed.get(i).getmImageResource());
//            }
//
//
//        }

//        countDownTimer.cancel();


//        startTimer();
//        counterCard.setImageResource(fiveExercisesPassed.get(1).getmImageResource());
//        startTimer();
//        counterCard.setImageResource(fiveExercisesPassed.get(2).getmImageResource());
//        startTimer();
//        counterCard.setImageResource(fiveExercisesPassed.get(1).getmImageResource());
//        startTimer();
//        counterCard.setImageResource(fiveExercisesPassed.get(0).getmImageResource());

//            startExercises();

//        for(i=0;i<5;i++){
//            if(isTimerRunning){
//                continue;
//            }
//            else{
//                counterCard.setImageResource(fiveExercisesPassed.get(i).getmImageResource());
//                startTimer();
//            }
//        }


        //Create congrats message
        if (today.getWorkoutDoneToday())
            createPopupDailog();


        recyclerView = findViewById(R.id.final_workout_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //setting the adpater
        recyclerViewAdapter = new RecyclerViewAdapter_finalTimer(fiveExercisesPassed);

        //using adapter to fill data into the recycler view
        updateAdapter();


    }

    private void createPopupDailog() {
        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.congrats, null);

        builder.setView(view);
        dialog = builder.create();
        dialog.show();

    }

    private void updateAdapter() {
        //using adapter to fill data into the recycler view
//        if (i==4)
//        {
//            today.setWorkoutDoneToday(true);
//        }
        recyclerView.setAdapter(recyclerViewAdapter);


    }

//    private void startExercises() {
//        while(i<4) {
//            if (isTimerRunning) {
//                continue;
//            }
//            else {
//                fiveExercisesPassed.get(i).setDone(true);
//                i++;
//                counterCard.setImageResource(fiveExercisesPassed.get(i).getmImageResource());
//                startTimer();
//            }
//        }
//    }

//    TODO: Make a method() that will accept integer as the parameter that will be the position
    // and this will prevent recursive call to the startTimer() again and again this will also
    // solve the music overlapping problem and the inital startTimer() method call too.


    private void startTimer() {
        countDownTimer = new CountDownTimer(time_left_in_millis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_in_millis = millisUntilFinished;
                updateProgressBar();
            }

            @Override
            public void onFinish() {
            isTimerRunning = false;
            if(player != null)
            stopMedia();
            restTime();
//                resetTimer();
                Log.d("Timer not running", "true");
            }
        }.start();
        isTimerRunning = true;
    }

    private void resetTimer() {


//    if(i<5 && isTimerRunning==false) {
//        i++;
//        counterCard.setImageResource(fiveExercisesPassed.get(i).getmImageResource());
//        startTimer();
//    }
//    countDownTimer.cancel();
        i++;
        if (i < 5) {
            if (player != null) {
                stopMedia();
            }
//            restTime();
            start_time_in_millis = (long) (time_in_sec * 1000 * fiveExercisesPassed.get(i).getTimePerRepinSec());
            time_left_in_millis = start_time_in_millis;
            progressBarTimer.setMax((int) start_time_in_millis / 1000);

            updateProgressBar();
            playMedia(i);
            counterCard.setImageResource(fiveExercisesPassed.get(i).getmImageResource());
            fiveExercisesPassed.get(i).setDone(true);
            startTimer();
            updateAdapter();
            Log.d("Reset and i", "resetTimer: done" + i);
        }
        if (i == 5) {
            today.setWorkoutDoneToday(true);
            Log.d("Workout done today ", String.valueOf(today.getWorkoutDoneToday()));
            Log.d("Workout Done time ", String.valueOf(today.getTime()));
            Log.d("Workout Done date", String.valueOf(today.getCurrentWeekDay()));
            stopMedia();
            if (today.getWorkoutDoneToday())
                createPopupDailog();
        }


//        if(i<5) {
//            i++;
//            counterCard.setImageResource(fiveExercisesPassed.get(i).getmImageResource());
//            startTimer();
//            fiveExercisesPassed.get(i).setDone(true);
//            updateAdapter();
//        }
    }

    private void restTime() {
//        builder1 = new AlertDialog.Builder(workout_timer.this);
//        View view = getLayoutInflater().inflate(R.layout.rest_timer, null);

//        skip_button = view.findViewById(R.id.skip_rest);
//        rest_time = view.findViewById(R.id.rest_timer);

        if(i<4) {
//            counterCard.setVisibility(View.GONE);
//            animationView.setVisibility(View.VISIBLE);
//                animationView.playAnimation();
            counterCard.setImageResource(R.drawable.hourglass);
            new CountDownTimer(20000, 1000) {

                public void onTick(long millisUntilFinished) {
                    time_left_in_millis = millisUntilFinished;
                    updateProgressBar();
                    counterCard.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onFinish();
                        }
                    });
                }

                public void onFinish() {
//               rest_time.setText("done!");
//                    dialog1.dismiss();
//                    animationView.cancelAnimation();
//                    animationView.setVisibility(View.GONE);
//                    counterCard.setVisibility(View.VISIBLE);
                    resetTimer();


                }

            }.start();
//            builder1.setView(view);
//            dialog1 = builder1.create();
//            dialog1.show();
        }
        if(i == 4)
            resetTimer();

//        skip_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog1.dismiss();
//            }
//        });

    }

    private void updateProgressBar() {

        int sec = (int) (time_left_in_millis / 1000) % 60;
        int min = (int) (time_left_in_millis / 1000) / 60;
        Log.d("Timer running", " true");
//        double percentageDec =Math.floor(time_left_in_millis/start_time_in_millis *100);
//        int percentage = (int) percentageDec%100;
        Log.d("Time left in millis", String.valueOf(time_left_in_millis));
        Log.d("total time = ", String.valueOf(start_time_in_millis));
        progressBarTimer.setProgress(sec);
        String timeLeftFormated = String.format("%02d:%02d", min, sec);
        clock.setText(timeLeftFormated);
    }


    private void playMedia(int x) {

        switch (x) {
            case 0:
            case 4:
                if (player == null)
                    player = MediaPlayer.create(this, R.raw.music1);
//                    player.setLooping(true);
                break;
            case 1:
                if (player == null)
                    player = MediaPlayer.create(this, R.raw.music2);
//                    player.setLooping(true);
                break;
            case 2:
                if (player == null)
                    player = MediaPlayer.create(this, R.raw.music3);
//                    player.setLooping(true);
                break;
            case 3:
                if (player == null)
                    player = MediaPlayer.create(this, R.raw.music4);
//                    player.setLooping(true);
                break;
        }

        player.start();
    }

    private void pauseMedia() {
        if (player != null)
            player.pause();
    }

    private void stopMedia() {
        stopPlayer();
        Log.d("Media closed", "true ");
    }

    private void stopPlayer() {
        if (player != null)
            player.release();
        player = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        pauseMedia();
        stopMedia();
        i=10;

    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseMedia();
        stopMedia();
        i=10;

    }


}