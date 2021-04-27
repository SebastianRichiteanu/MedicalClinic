package com.example.medicalclinic2.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.time.LocalDate;

public class DatabaseHandler extends SQLiteOpenHelper {

    // DB Version
    private static final int DATABASE_VERSION = 1;

    // DB Name
    private static final String DATABASE_NAME = "test.db";

    // Table Name
    private static final String TABLE_NAME_USER = "userdata";
    private static final String TABLE_NAME_PATIENT = "patientdata";
    private static final String TABLE_NAME_MEDICATION = "medicationdata";
    private static final String TABLE_NAME_DOCTOR = "doctordata";
    private static final String TABLE_NAME_SUPPLIER = "supplierdata";
    private static final String TABLE_NAME_MEDPRESC = "medprescdata";
    private static final String TABLE_NAME_PRESCRIPTION = "prescriptiondata";
    private static final String TABLE_NAME_APPOINTMENT = "appointmentdata";


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
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_IDMEDICATION = "idMedication";
    private static final String COLUMN_IDPRESCRIPTION = "idPrescription";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_IDDOCTOR = "idDoctor";
    private static final String COLUMN_IDPATIENT = "idPatient";


    SQLiteDatabase database;

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE " + TABLE_NAME_USER + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_MEDICATION + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + COLUMN_PRICE  + " DECIMAL(10,2), " + COLUMN_SUPPLIER + " INTEGER)" );

        db.execSQL("CREATE TABLE " + TABLE_NAME_DOCTOR + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + COLUMN_SURNAME + " TEXT, " + COLUMN_AGE + " INTEGER, " + COLUMN_ADDRESS +
                " TEXT, " + COLUMN_PHONE + " TEXT, " + COLUMN_SALARY + " DECIMAL(10,2), " + COLUMN_SPECIALIZATION +
                " TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_NAME_PATIENT + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, " + COLUMN_SURNAME + " TEXT, " + COLUMN_AGE + " INTEGER, " + COLUMN_ADDRESS
            + " TEXT, " + COLUMN_PHONE + " TEXT, " + COLUMN_CONDITION + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_SUPPLIER + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + COLUMN_LOCATION + " TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_NAME_MEDPRESC + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_IDMEDICATION + " INTEGER, " + COLUMN_IDPRESCRIPTION + " INTEGER)");

        db.execSQL("CREATE TABLE " + TABLE_NAME_PRESCRIPTION + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " DATE) ");
        db.execSQL("CREATE TABLE " + TABLE_NAME_APPOINTMENT + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_IDDOCTOR + " INTEGER, " + COLUMN_IDPATIENT + " INTEGER, " + COLUMN_DATE + " DATE) ") ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MEDICATION);
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DOCTOR);
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PATIENT);
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MEDPRESC);
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SUPPLIER);
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MEDPRESC);
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PRESCRIPTION);
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_APPOINTMENT);
       onCreate(db);
    }
    public void deleteAllDoctors() {
        database = getWritableDatabase();
        database.execSQL("delete from " + TABLE_NAME_DOCTOR);
    }
    public void deleteAllMedications() {
        database = getWritableDatabase();
        database.execSQL("delete from " + TABLE_NAME_MEDICATION);
    }
    public void deleteAllUsers() {
        database = getWritableDatabase();
        database.execSQL("delete from " + TABLE_NAME_USER);
    }
    public void deleteAllPatients(){
        database = getWritableDatabase();
        database.execSQL("delete from " + TABLE_NAME_PATIENT);
    }
    public void deleteAllSuppliers(){
        database = getWritableDatabase();
        database.execSQL("delete from "+ TABLE_NAME_SUPPLIER);
    }
    public void deleteAllMedPresc(){
        database = getWritableDatabase();
        database.execSQL("delete from "+ TABLE_NAME_MEDPRESC);
    }
    public void deleteAllPrescriptions(){
        database = getWritableDatabase();
        database.execSQL("delete from "+ TABLE_NAME_PRESCRIPTION);
    }
    public void deleteAllAppointments(){
        database = getWritableDatabase();
        database.execSQL("delete from "+ TABLE_NAME_APPOINTMENT);
    }

    public void deleteAll(){
        deleteAllUsers();
        deleteAllDoctors();
        deleteAllMedications();
        deleteAllPatients();
        deleteAllSuppliers();
        deleteAllMedPresc();
        deleteAllPrescriptions();
        deleteAllAppointments();
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
        long result = database.insert(TABLE_NAME_USER, null, initial);
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
        long result = database.insert(TABLE_NAME_SUPPLIER, null, initial);
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
        long result = database.insert(TABLE_NAME_MEDPRESC, null, initial);
        if(result == -1)
            return false;
        else {
            return true;
        }
    }

    public boolean insertMedication(String name, Double price, int supplier){
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

    public boolean insertPrescription(Date date){
        database = getWritableDatabase();
        ContentValues initial = new ContentValues();
        initial.put("date", String.valueOf(date));
        long result = database.insert(TABLE_NAME_PRESCRIPTION, null, initial);
        if(result == -1)
            return false;
        else {
            return true;
        }
    }

    public boolean insertAppointment(int idDoctor, int idPatient, Date date){
        database = getWritableDatabase();
        ContentValues initial = new ContentValues();
        initial.put("idDoctor", String.valueOf(idDoctor));
        initial.put("idPatient", String.valueOf(idPatient));
        initial.put("date", String.valueOf(date));
        long result = database.insert(TABLE_NAME_APPOINTMENT, null, initial);
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
    public Cursor allDataPrescription(){
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from prescriptiondata", null);
        return cursor;
    }
    public Cursor allDataAppointments(){
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from appointmentdata", null);
        return cursor;
    }


}
