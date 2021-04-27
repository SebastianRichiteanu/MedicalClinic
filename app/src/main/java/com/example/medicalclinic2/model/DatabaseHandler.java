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

    private static final String TABLE_NAME_PATIENT = "patientdata";
    private static final String TABLE_NAME_MEDICATION = "medicationdata";
    private static final String TABLE_NAME_DOCTOR = "doctordata";


    private static final String SUPPLIER_TABLE_NAME = "supplierdata";
    private static final String MEDPRESC_TABLE_NAME = "medprescdata";


    // Table Fields
    private static final String COLUMN_ID = "id";

    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";


    private static final String COLUMN_NAME = "name";

    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_SUPPLIER = "supplier";

    private static final String COLUMN_SURNAME = "surname";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_PHONE = "phone";

    private static final String COLUMN_SALARY = "salary";
    private static final String COLUMN_SPECIALIZATION = "specialization";

    private static final String COLUMN_CONDITION = "condition";

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
        db.execSQL("CREATE_TABLE " + TABLE_NAME_MEDICATION + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + COLUMN_PRICE  + " DECIMAL(10,2), " + COLUMN_SUPPLIER + " TEXT)" );

        db.execSQL("CREATE TABLE " + TABLE_NAME_DOCTOR + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + COLUMN_SURNAME + " TEXT, " + COLUMN_AGE + " INTEGER, " + COLUMN_ADDRESS +
                " TEXT, " + COLUMN_PHONE + " TEXT, " + COLUMN_SALARY + " DECIMAL(10,2), " + COLUMN_SPECIALIZATION +
                " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_PATIENT + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, " + COLUMN_SURNAME + " TEXT, " + COLUMN_AGE + " INTEGER, " + COLUMN_ADDRESS
            + " TEXT, " + COLUMN_PHONE + " TEXT, " + COLUMN_CONDITION + " TEXT)");
        db.execSQL("CREATE TABLE " + SUPPLIER_TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + COLUMN_LOCATION + " TEXT)");
        db.execSQL("CREATE TABLE " + MEDPRESC_TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_IDMEDICATION + " INTEGER, " + COLUMN_IDPRESCRIPTION + " INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MEDICATION);
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DOCTOR);
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PATIENT);
       onCreate(db);
    }
    public void deleteAllDoctors() {
        database = getWritableDatabase();
        database.execSQL("delete from " + TABLE_NAME_DOCTOR);
    }
    public void deleteAllMedication(){
        database = getWritableDatabase();
        database.execSQL("delete from "+ TABLE_NAME_MEDICATION);
    public void deleteAllUsers() {
        database = getWritableDatabase();
        database.execSQL("delete from " + TABLE_NAME);
    }
    public void deleteAllPatients(){
        database = getWritableDatabase();
        database.execSQL("delete from " + TABLE_NAME_PATIENT);
    }
    public void deleteAllSupplier(){
        database = getWritableDatabase();
        database.execSQL("delete from "+ SUPPLIER_TABLE_NAME);
    }

    public void deleteAllMedPresc(){
        database = getWritableDatabase();
        database.execSQL("delete from "+ MEDPRESC_TABLE_NAME);
    }
    public void deleteAll(){
        deleteAllUsers();
        deleteAllDoctors();
        deleteAllMedication();
        deleteAllPatients();
        deleteAllSupplier();
        deleteAllMedPresc();
    }
   


    public void onUpgradeSupplier(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SUPPLIER_TABLE_NAME);
        onCreate(db);
    }

    public void onUpgradeMedPresc(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MEDPRESC_TABLE_NAME);
        onCreate(db);

    }


    public boolean insertPatient(String name, String surname, int age, String address, String phone, String condition){
        database = getWritableDatabase();
        ContentValues initial = new ContentValues();
        initial.put("name", name);
        initial.put("surname", surname);
        initial.put("age", age);
        initial.put("address", address);
        initial.put("phone", phone);
        initial.put("condition", condition);
        long result = database.insert(TABLE_NAME_PATIENT, null, initial);
        if(result == -1)
            return false;
        else {
            return true;
        }

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

    public Cursor allDataSuppliers(){
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from supplierdata", null);
        return cursor;
    }

    public Cursor allDataMedication(){
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from medicationdata", null);
        return cursor;
    }
    public Cursor allDataDoctors(){
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from doctordata", null);
        return cursor;
    }
    public Cursor allDataPatients(){
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from patientdata", null);
        return cursor;
    }
    public Cursor allDataMedPrescs(){
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from medprescdata", null);
        return cursor;
    }

}
