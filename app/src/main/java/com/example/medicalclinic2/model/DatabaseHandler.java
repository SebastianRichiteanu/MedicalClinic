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
    private static final String SUPPLIER_TABLE_NAME = "supplierdata";
    private static final String MEDPRESC_TABLE_NAME = "medprescdata";

    // Table Fields
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    // Supplier Table Data
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_LOCATION = "location";

    // MedicationInPrescription Table Data
    private static final String COLUMN_IDMEDICATION = "idMedication";
    private static final String COLUMN_IDPRESCRIPTION = "idPrescription";

    SQLiteDatabase database;

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT)");
        db.execSQL("CREATE TABLE " + SUPPLIER_TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + COLUMN_LOCATION + " TEXT)");
        db.execSQL("CREATE TABLE " + MEDPRESC_TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_IDMEDICATION + " INTEGER, " + COLUMN_IDPRESCRIPTION + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
       onCreate(db);
    }

    public void onUpgradeSupplier(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SUPPLIER_TABLE_NAME);
        onCreate(db);
    }

    public void onUpgradeMedPresc(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MEDPRESC_TABLE_NAME);
        onCreate(db);
    }

    public void deleteAll(){
        database = getWritableDatabase();
        database.execSQL("delete from "+ TABLE_NAME);
    }

    public void deleteAllSupplier(){
        database = getWritableDatabase();
        database.execSQL("delete from "+ SUPPLIER_TABLE_NAME);
    }

    public void deleteAllMedPresc(){
        database = getWritableDatabase();
        database.execSQL("delete from "+ MEDPRESC_TABLE_NAME);
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

    public boolean insertSupplier(String name, String location) {
        database = getWritableDatabase();
        ContentValues initial = new ContentValues();
        initial.put("name", name);
        initial.put("location", location);
        long result = database.insert(SUPPLIER_TABLE_NAME, null, initial);
        if(result == -1)
            return false;
        else {
            return true;
        }
    }

    public boolean insertMedPresc(int idMed, int idPresc) {
        database = getWritableDatabase();
        ContentValues initial = new ContentValues();
        initial.put("idMedication", idMed);
        initial.put("idPrescription", idPresc);
        long result = database.insert(MEDPRESC_TABLE_NAME, null, initial);
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

    public Cursor allDataSuppliers(){
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from supplierdata", null);
        return cursor;
    }

    public Cursor allDataMedPrescs(){
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from medprescdata", null);
        return cursor;
    }
}
