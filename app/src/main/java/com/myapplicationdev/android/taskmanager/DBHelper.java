package com.myapplicationdev.android.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    // Start version with 1
    // increment by 1 whenever db schema changes.
    private static final int DATABASE_VER = 1;

    // Filename of the database
    private static final String DATABASE_NAME = "tasks.db";

    private static final String TABLE_TASK = "task";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SECONDS = "seconds";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_TASK
                +  "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT," + COLUMN_DESCRIPTION + " TEXT,"+ COLUMN_SECONDS + " INTEGER )";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, "Buy milk");
        values.put(COLUMN_DESCRIPTION, "Low fat");
        values.put(COLUMN_SECONDS, 5);
        db.insert(TABLE_TASK, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        // Create table(s) again
        onCreate(db);


    }

    public long insertTask(String name, String description, int seconds) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME , name);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_SECONDS, seconds);

        long result = db.insert(TABLE_TASK, null, values);
        db.close();

        if (result == -1) {
            Log.d("DBHelper", "Insert failed");
        }

        Log.d("SQL Insert ",""+ result); //id returned, shouldn’t be -1
        return result;
    }


    public ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_DESCRIPTION + ", "
                + COLUMN_NAME
                + " FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String description = cursor.getString(1);
                String name = cursor.getString(2);
                Task obj = new Task(id, description, name);
                tasks.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }


}
