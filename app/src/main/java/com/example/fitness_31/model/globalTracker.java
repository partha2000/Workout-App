package com.example.fitness_31.model;

import android.media.MediaPlayer;
import android.util.Log;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.text.DateFormat.DAY_OF_WEEK_FIELD;
import static java.text.DateFormat.DAY_OF_WEEK_IN_MONTH_FIELD;
import static java.text.DateFormat.FULL;
import static java.text.DateFormat.SHORT;

//TODO: Communicate with the data base through this model globalTracker.
// Although a controller should be used as a name here :)


public class globalTracker {


    private Boolean workoutDoneToday;
    private Calendar today;
    private String currentWeekDay;
    private double timeWorkedOut;
    private int id;

    public globalTracker(Boolean workoutDoneToday, double time) {
        this.workoutDoneToday = workoutDoneToday;
        this.today = today.getInstance();
        this.currentWeekDay = DateFormat.getDateInstance(SHORT)
                .format(this.today.getTime());
        this.timeWorkedOut= time;
        Log.d("created an daily object","Week day = "+this.currentWeekDay);
    }

    public globalTracker(Boolean workoutDoneToday, double timeWorkedOut, int id) {
        this.workoutDoneToday = workoutDoneToday;
        this.timeWorkedOut = timeWorkedOut;
        this.id = id;
    }

    public globalTracker() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getWorkoutDoneToday() {
        return workoutDoneToday;
    }

    public Calendar getToday() {
        return today;
    }

    public double getTime() {
        return timeWorkedOut;
    }

    public void setWorkoutDoneToday(Boolean workoutDoneToday) {
        this.workoutDoneToday = workoutDoneToday;
    }

    public void setCurrentWeekDay(String currentWeekDay) {
        this.currentWeekDay = currentWeekDay;
    }

    public void setTimeWorkedOut(double timeWorkedOut) {
        this.timeWorkedOut = timeWorkedOut;
    }

    public void setToday(Calendar today) {
        this.today = today;
    }

    public void setTime(double time) {
        this.timeWorkedOut = time;
    }

    public String getCurrentWeekDay() {
        return currentWeekDay;
    }

    public double getTimeWorkedOut() {
        return timeWorkedOut;
    }
}
