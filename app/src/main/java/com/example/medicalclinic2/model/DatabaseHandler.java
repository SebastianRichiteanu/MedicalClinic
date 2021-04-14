package com.example.medicalclinic2.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

    // DB Version
    private static final int DATABASE_VERSION = 1;

    // DB Name
    private static final String  DATABASE_NAME = "test.db";

    // Table Name
    private static final String TABLE_NAME = "userdata";

    // Table Fields
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    SQLiteDatabase database;

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
       onCreate(db);

    }

    public void deleteAll(){
        database = getWritableDatabase();
        database.execSQL("delete from "+ TABLE_NAME);
    }

    public boolean insertUser(int id, String username, String password){
        database = getWritableDatabase();
        ContentValues initial = new ContentValues();
        initial.put("id", id);
        initial.put("username", username);
        initial.put("password", password);
        long result = database.insert(TABLE_NAME, null, initial);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor allData(){
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from userdata", null);
        return cursor;
    }

}
