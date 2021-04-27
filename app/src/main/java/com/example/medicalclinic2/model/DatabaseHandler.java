package com.example.medicalclinic2.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

    // DB Version
    private static final int DATABASE_VERSION = 1;

    // DB Name
    private static final String  DATABASE_NAME = "test.db";

    // Table Name
    private static final String TABLE_NAME = "userdata";

    private static final String TABLE_NAME_MEDICATION = "medicationdata";

    // Table Fields
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_SUPPLIER = "supplier";

    private static int db_user_id = 1;   // se reseteaza de fiecare data ???

    SQLiteDatabase database;

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT)");
        db.execSQL("CREATE_TABLE " + TABLE_NAME_MEDICATION + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + COLUMN_PRICE  + " DECIMAL(10,2), " + COLUMN_SUPPLIER + " TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MEDICATION);
       onCreate(db);

    }

    public void deleteAllMedication(){
        database = getWritableDatabase();
        database.execSQL("delete from "+ TABLE_NAME_MEDICATION);
    }

    public boolean insertUser(String username, String password){
        database = getWritableDatabase();
        ContentValues initial = new ContentValues();
        initial.put("id", db_user_id);
        initial.put("username", username);
        initial.put("password", password);
        long result = database.insert(TABLE_NAME, null, initial);
        if(result == -1)
            return false;
        else {
            db_user_id += 1;
            return true;
        }
    }

    public boolean insertMedication(String name, Double price, String supplier){
        database = getWritableDatabase();
        ContentValues initial = new ContentValues();
        initial.put("name", name);
        initial.put("price", price);
        initial.put("supplier", supplier);
        long result = database.insert(TABLE_NAME_MEDICATION, null, initial);
        if(result == -1)
            return false;
        else {
            return true;
        }
    }

    public Cursor allData(){
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from userdata", null);
        return cursor;
    }

    public Cursor allDataMedication(){
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from medicationdata", null);
        return cursor;
    }

}
