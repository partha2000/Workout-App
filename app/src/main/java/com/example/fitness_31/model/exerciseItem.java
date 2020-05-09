package com.example.fitness_31.model;


import java.io.Serializable;

public class exerciseItem implements Serializable {
    private int mImageResource;
    private String name;
    private String repetationsSelected;
    private Boolean done;
    private double timePerRepinSec;

    public exerciseItem(int mImageResource, String name, String duration, Boolean done,double timePerRep) {
        this.mImageResource = mImageResource;
        this.name = name;
        this.repetationsSelected = String.valueOf(Math.round(Integer.parseInt(duration)*timePerRep));

        this.done = done;
        this.timePerRepinSec = timePerRep;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getName() {
        return name;
    }

    public String getRepetationsSelected() {
        return repetationsSelected;
    }

    public Boolean getDone() {
        return done;
    }
    public double getTimePerRepinSec() {
        return timePerRepinSec;
    }
    public void setDone(Boolean done) {
        this.done = done;
    }


}
