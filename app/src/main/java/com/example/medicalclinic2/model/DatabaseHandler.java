package com.example.medicalclinic2.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

    // DB Version
    private static final int DATABASE_VERSION = 1;

    // DB Name
    private static final String DATABASE_NAME = "test.db";

    // Table Name
    private static final String TABLE_NAME = "userdata";

    private static final String TABLE_NAME_DOCTOR = "doctordata";

    // Table Fields
    private static final String COLUMN_ID = "id";

    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SURNAME = "surname";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_SALARY = "salary";
    private static final String COLUMN_SPECIALIZATION = "specialization";

    SQLiteDatabase database;

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_DOCTOR + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + COLUMN_SURNAME + " TEXT, " + COLUMN_AGE + " INTEGER, " + COLUMN_ADDRESS +
                " TEXT, " + COLUMN_PHONE + " TEXT, " + COLUMN_SALARY + " DECIMAL(10,2), " + COLUMN_SPECIALIZATION +
                " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DOCTOR);
       onCreate(db);
    }

    public void deleteAllDoctors() {
        database = getWritableDatabase();
        database.execSQL("delete from " + TABLE_NAME_DOCTOR);
    }

    public void deleteAllUsers() {
        database = getWritableDatabase();
        database.execSQL("delete from " + TABLE_NAME);
    }

    public void deleteAll(){
        deleteAllUsers();
        deleteAllDoctors();
    }

    public boolean insertUser(String username, String password){
        database = getWritableDatabase();
        ContentValues initial = new ContentValues();
        initial.put("username", username);
        initial.put("password", password);
        long result = database.insert(TABLE_NAME, null, initial);
        if(result == -1)
            return false;
        else {
            return true;
        }
    }

    public boolean insertDoctor(String name, String surname, int age, String address, String phone, double salary, String specialization){
        database = getWritableDatabase();
        ContentValues initial = new ContentValues();
        initial.put("name", name);
        initial.put("surname", surname);
        initial.put("age", age);
        initial.put("address", address);
        initial.put("phone", phone);
        initial.put("salary", salary);
        initial.put("specialization", specialization);
        long result = database.insert(TABLE_NAME_DOCTOR, null, initial);
        if(result == -1)
            return false;
        else {
            return true;
        }
    }

    public Cursor allDataUsers(){
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from userdata", null);
        return cursor;
    }

    public Cursor allDataDoctors(){
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from doctordata", null);
        return cursor;
    }


}
