package com.example.fitness_31.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.fitness_31.model.exerciseItem;
import com.example.fitness_31.model.globalTracker;
import com.example.fitness_31.util.util;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDatabase extends SQLiteOpenHelper {

    //This is the coustructor we need to make
    public WorkoutDatabase(Context context) {
        super(context, util.DATABASE_NAME, null, util.DATABASE_VERSION);
    }

    // ========We create our tables here========
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(util.CREATE_RECORD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //DROP_TABLE= "DROP TABLE IF EXISTS"
        db.execSQL(util.DROP_TABLE, new String[]{util.DATABASE_NAME});

        //Create a new table again
        onCreate(db);
    }

    //crud COMMANDS
    public void addRecord(globalTracker record){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(util.KEY_DATE,record.getCurrentWeekDay());
        values.put(util.KEY_TIME_SPENT,record.getTimeWorkedOut());
        values.put(util.KEY_WORKOUT_DONE,record.getWorkoutDoneToday());

        //insert to a row
        db.insert(util.TABLE_NAME,null,values);
        Log.d("DBhandler","added date : "+values.get("date"));
        db.close(); // closing db connection
    }

    //Get contacts
    public globalTracker getRecord(int id){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.query(util.TABLE_NAME,
                new String[]{util.KEY_ID,util.KEY_DATE,util.KEY_TIME_SPENT,
                        util.KEY_WORKOUT_DONE},
                util.KEY_ID+"=?", new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        //creates contact object and retrieves the data.
        globalTracker record = new globalTracker();
        record.setId(Integer.parseInt(cursor.getString(0)));
        record.setCurrentWeekDay(cursor.getString(1));
        record.setTimeWorkedOut(cursor.getInt(2));
        record.setWorkoutDoneToday(Boolean.valueOf(cursor.getString(3)));

        return record;
    }
    public globalTracker getRecord(String date){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.query(util.TABLE_NAME,
                new String[]{util.KEY_ID,util.KEY_DATE,util.KEY_TIME_SPENT,
                        util.KEY_WORKOUT_DONE},
                util.KEY_DATE+"=?", new String[]{String.valueOf(date)},
                null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        //creates contact object and retrieves the data.
        globalTracker record = new globalTracker();
        record.setId(Integer.parseInt(cursor.getString(0)));
        record.setCurrentWeekDay(cursor.getString(1));
        record.setTimeWorkedOut(cursor.getInt(2));
        record.setWorkoutDoneToday(Boolean.valueOf(cursor.getString(3)));

        return record;
    }

    //get all contacts
    public List<globalTracker> getAllRecords(){

        List<globalTracker> recordList = new ArrayList<>();

        SQLiteDatabase db =this.getReadableDatabase();
        //select all contacts


        Cursor cursor = db.rawQuery(util.SELECT_ALL,null);

        //Loop through the data
        if(cursor.moveToFirst()) {
            do {
                globalTracker record = new globalTracker();
                record.setId(Integer.parseInt(cursor.getString(0)));
                record.setCurrentWeekDay(cursor.getString(1));
                record.setTimeWorkedOut(cursor.getInt(2));
                record.setWorkoutDoneToday(Boolean.valueOf(cursor.getString(3)));
                //add contact objects to our list
                recordList.add(record);
                Log.d("arrayList_accessed","array list accessed");
            } while (cursor.moveToNext());

        }

        return recordList;

    }

//    public int updateRecord(contact contact) {
//
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(util.KEY_NAME,contact.getName());
//        values.put(util.KEY_PHONE_NUMBER,contact.getPhone());
//
//
//        //update(table_name,values, where id =2)
//
//        return db.update(util.TABLE_NAME,values,util.
//                KEY_ID+"=?",new String[]{String.valueOf(contact.getId())});
//
//    }

    //delete a contact
    public void deleteContact(globalTracker record){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(util.TABLE_NAME,util.KEY_ID+"=?",
                new String[]{String.valueOf(record.getId())});

        db.close();
    }

    //Get contacts count
    public int getRowCount(){
        String countQuery ="SELECT * FROM "+util.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.rawQuery(countQuery,null);

        return cursor.getCount();
    }
}
//TODO : Create a sql local database to store the data to the workout database
// this will be used to populate the recyceler view in the stats fragment.
// In the stats fragment make a daily logger type window complete this quickly to move on to completion.