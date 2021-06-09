package com.example.medicalclinic2;

import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import com.example.medicalclinic2.model.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    private Button myprofile_button;
    private Button doctors_button;
    private Button appointments_button;
    public DatabaseHandler databaseHandler;
    public SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        NavigationView navigationView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sp = getSharedPreferences("login", MODE_PRIVATE);


        databaseHandler = new DatabaseHandler(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            System.out.println("AAAAAAAAA");
            String method = extras.getString("method", "");
            if (method.equals("Register")) {
                Intent i = new Intent(MainActivity.this, ProfileView.class);
                String username = extras.getString("username");
                String password = extras.getString("password");
                String role = extras.getString("role");
                databaseHandler.insertUser(username, password, role);
                startActivity(i);
            } else {
                System.out.println("BBBBBBBBB");
                String username = sp.getString("username", "");
                String name = extras.getString("name");
                String surname = extras.getString("surname");
                int age = extras.getInt("age");
                String address = extras.getString("address");
                String phoneNo = extras.getString("phoneNo");

                if (sp.getString("role", "").equals("Patient")) {
                    System.out.println("CCCCCCCCC");
                    String condition = extras.getString("condition");
                    Cursor cursorPatient = databaseHandler.searchUserInPatients(username);
                    boolean found = false;
                    while(cursorPatient.moveToNext()){
                        found = true;
                    }
                    System.out.println(found);
                    if (found)
                        databaseHandler.editPatient(name, surname, age, address, phoneNo, condition, username);
                    else
                        databaseHandler.insertPatient(name, surname, age, address, phoneNo, condition, username);
                } else if (sp.getString("role", "").equals("Doctor")) {
                    System.out.println("DDDDDDDD");
                    String specialization = extras.getString("specialization");
                    Cursor cursorDoctor = databaseHandler.searchUserInDoctors(username);
                    boolean found = false;
                    while (cursorDoctor.moveToNext()) {
                        found = true;
                    }
                    System.out.println(found);
                    if (found) {
                        databaseHandler.editDoctor(name, surname, age, address, phoneNo, specialization, username);
                        System.out.println("AM MODIFICAT!!!!!!!!!!!!!!" + username);
                    }
                    else {
                        databaseHandler.insertDoctor(name, surname, age, address, phoneNo, specialization, username);

                    }
                }



            }
        }

        System.out.println("USERI!!!!");
        Cursor cursor = databaseHandler.allDataUsers();

        if(cursor.getCount() == 0)
            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();
        else{
            while(cursor.moveToNext()){
                System.out.println("Id: " + cursor.getString(0));
                System.out.println("Username: " + cursor.getString(1));
                System.out.println("Password: " + cursor.getString(2));
                System.out.println("Role: " +  cursor.getString(3));
                if (cursor.getString(3).equals("Patient")) {
                    Cursor cursor3 = databaseHandler.searchUserInPatients(cursor.getString(1));
                    while(cursor3.moveToNext()) {
                        System.out.println("Name:" + cursor3.getString(1));
                        System.out.println("Surname:" + cursor3.getString(2));
                        System.out.println("Age:" + cursor3.getString(3));
                        System.out.println("Address:" + cursor3.getString(4));
                        System.out.println("Phone:" + cursor3.getString(5));
                        System.out.println("Condition:" + cursor3.getString(6));
                        System.out.println("Username:" + cursor3.getString(7));
                    }
                } else {
                    Cursor cursor3 = databaseHandler.searchUserInDoctors(cursor.getString(1));
                    while(cursor3.moveToNext()) {
                        System.out.println("Name:" + cursor3.getString(1));
                        System.out.println("Surname:" + cursor3.getString(2));
                        System.out.println("Age:" + cursor3.getString(3));
                        System.out.println("Address:" + cursor3.getString(4));
                        System.out.println("Phone:" + cursor3.getString(5));
                        System.out.println("Specialization:" + cursor3.getString(6));
                        System.out.println("Username:" + cursor3.getString(7));
                    }
                }
            }
        }

        System.out.println("DOCTORIiIIIIIIIIIIIIIIIIIIIIIIIIIII");
        Cursor cursor5 = databaseHandler.allDataDoctors();
        if(cursor5.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();}
        else{
            while(cursor5.moveToNext()){
                System.out.println("Id: " + cursor5.getString(0));
                System.out.println("Name: " + cursor5.getString(1));
                System.out.println("Surname: " + cursor5.getString(2));
                System.out.println("Age: " + cursor5.getString(3));
                System.out.println("Address: " + cursor5.getString(4));
                System.out.println("Phone: " + cursor5.getString(5));
                System.out.println("Specialization: " + cursor5.getString(6));
                System.out.println("Username: " + cursor5.getString(7));

            }
        }

        System.out.println("APOINTMENTS");
        Cursor cursor7 = databaseHandler.allDataAppointments();
        if(cursor7.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();}
        else{
            while(cursor7.moveToNext()){
                System.out.println("Id: " + cursor7.getString(0));
                System.out.println("idDoctor: " + cursor7.getString(1));
                System.out.println("idPatient: " + cursor7.getString(2));
                System.out.println("data: " + cursor7.getString(3));
            }
        }

        myprofile_button = (Button) findViewById(R.id.profileview);
        doctors_button = (Button) findViewById(R.id.doctors);
        appointments_button = (Button) findViewById(R.id.appointments);

        System.out.println(sp.getBoolean("logged", false));

        if (sp.getBoolean("logged", false) == false) {
            myprofile_button.setVisibility(View.INVISIBLE);
            doctors_button.setVisibility(View.INVISIBLE);
            appointments_button.setVisibility(View.INVISIBLE);
        } else {
            myprofile_button.setVisibility(View.VISIBLE);
            myprofile_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, ProfileView.class);
                    startActivity(i);
                }
            });

            String role = sp.getString("role", "");

            if (role.equals("Patient")) {
                doctors_button.setVisibility(View.INVISIBLE);
                appointments_button.setVisibility(View.VISIBLE);

                appointments_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, AppointmentsActivity.class);
                        startActivity(intent);
                    }
                });
            } else if (role.equals("Doctor")) {
                doctors_button.setVisibility(View.VISIBLE);
                appointments_button.setVisibility(View.INVISIBLE);

                doctors_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, DoctorActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if(sp.getBoolean("logged", false)){
            MenuItem item = menu.findItem(R.id.logout);
            item.setVisible(true);
            MenuItem itemReg = menu.findItem(R.id.register);
            itemReg.setVisible(false);
            MenuItem items = menu.findItem(R.id.login);
            items.setVisible(false);
        }
        else {
            MenuItem item = menu.findItem(R.id.login);
            item.setVisible(true);
            MenuItem items = menu.findItem(R.id.logout);
            items.setVisible(false);
            MenuItem itemReg = menu.findItem(R.id.register);
            itemReg.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.login){
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
        if (id == R.id.register) {
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        }
        if (id == R.id.logout) {
            Intent intent = new Intent(this, MainActivity.class);
            sp.edit().putBoolean("logged", false).apply();
            startActivity(intent);
        }
        if (id == R.id.action_settings) {
            System.out.println("Settings");
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
/*        if (id == R.id.profileview) {
            Intent intent = new Intent(this, ProfileView.class);
            startActivity(intent);
        }
        if (id == R.id.appointments) {
            Intent intent = new Intent(this, AppointmentsActivity.class);
            startActivity(intent);
        }
        if (id == R.id.doctors) {
            Intent intent = new Intent(this, DoctorActivity.class);
            startActivity(intent);
        }*/


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed () {
//        super .onClose(); ;
        System.out.println("STOPP");
        if (sp.getBoolean("logged", false) && sp.getString("role","").equals("Patient")) {
            String username = sp.getString("username", "user");
            Cursor idUsername = databaseHandler.getPatientIdByUsername(username);

            int id = -1;
            while(idUsername.moveToNext()){
                System.out.println("Id: " + idUsername.getInt(0));
                id = idUsername.getInt(0);
            }
            System.out.println("STOP ID");
            System.out.println(id);
            if (id != -1) {
                LocalDate localDate = java.time.LocalDate.now();
                Cursor cursor = databaseHandler.checkAppointment(id, localDate);
                if (cursor.getCount() != 0) {
                    System.out.println("NOTI");
                    startService(new Intent(this, NotificationService.class));
                }
            }
        }
        finish();
    }
//    public void closeApp (View view) {
//        finish() ;
//    }
}