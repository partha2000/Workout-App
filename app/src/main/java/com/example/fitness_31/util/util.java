package com.example.fitness_31.util;

import com.github.mikephil.charting.utils.Utils;

public class util {

    //Database related items
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME ="daily_tacker";
    public static final String TABLE_NAME ="workout_record";

    // contacts table column names

    public static final String KEY_ID="id";
    public static final String KEY_DATE="date";
    public static final String KEY_TIME_SPENT ="time_spent";
    public static final String KEY_WORKOUT_DONE = "workout_done";

    //Some SQL commands
    public static final String DROP_TABLE ="DROP TABLE IF EXISTS";
    public static final String CREATE_RECORD_TABLE = "CREATE TABLE "+util.TABLE_NAME+"("
            +util.KEY_ID+" INTEGER PRIMARY KEY,"+util.KEY_DATE+" DATE,"
            +util.KEY_TIME_SPENT+" INT,"+ util.KEY_WORKOUT_DONE+" BIT"+")";
    public static final String SELECT_ALL  = "SELECT * FROM "+util.TABLE_NAME;
    public static final String SELECT_DATES = "SELECT "+util.KEY_DATE+" FROM "+util.TABLE_NAME;
    public static final String SELECT_TIME_SPENT = "SELECT "+util.KEY_TIME_SPENT+" FROM "+util.TABLE_NAME;
    public static final String SELECT_WORKOUT_DONE = "SELECT "+util.KEY_WORKOUT_DONE+" FROM "+util.TABLE_NAME;
    public static final String SELECT_DATE_TIME_SPENT ="SELECT "+util.KEY_DATE+","+util.KEY_TIME_SPENT
            +" FROM ";
}
