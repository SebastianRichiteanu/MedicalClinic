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
    public SharedPreferences sp_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        NavigationView navigationView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        sp_settings = getSharedPreferences("settings", MODE_PRIVATE);

        databaseHandler = new DatabaseHandler(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String method = extras.getString("method", "");
            if (method.equals("Register")) {
                Intent i = new Intent(MainActivity.this, ProfileView.class);
                String username = extras.getString("username");
                String password = extras.getString("password");
                String role = extras.getString("role");
                databaseHandler.insertUser(username, password, role);
                startActivity(i);
            } else {
                String username = sp.getString("username", "");
                String name = extras.getString("name");
                String surname = extras.getString("surname");
                int age = extras.getInt("age");
                String address = extras.getString("address");
                String phoneNo = extras.getString("phoneNo");

                if (sp.getString("role", "").equals("Patient")) {
                    String condition = extras.getString("condition");
                    Cursor cursorPatient = databaseHandler.searchUserInPatients(username);
                    boolean found = false;
                    while(cursorPatient.moveToNext()){
                        found = true;
                    }
                    if (found)
                        databaseHandler.editPatient(name, surname, age, address, phoneNo, condition, username);
                    else
                        databaseHandler.insertPatient(name, surname, age, address, phoneNo, condition, username);
                } else if (sp.getString("role", "").equals("Doctor")) {
                    String specialization = extras.getString("specialization");
                    Cursor cursorDoctor = databaseHandler.searchUserInDoctors(username);
                    boolean found = false;
                    while (cursorDoctor.moveToNext()) {
                        found = true;
                    }
                    if (found) {
                        databaseHandler.editDoctor(name, surname, age, address, phoneNo, specialization, username);
                    }
                    else {
                        databaseHandler.insertDoctor(name, surname, age, address, phoneNo, specialization, username);

                    }
                }



            }
        }



        myprofile_button = (Button) findViewById(R.id.profileview);
        doctors_button = (Button) findViewById(R.id.doctors);
        appointments_button = (Button) findViewById(R.id.appointments);


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
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed () {
        if (sp.getBoolean("logged", false) && sp.getString("role","").equals("Patient")) {
            String username = sp.getString("username", "user");
            Cursor idUsername = databaseHandler.getPatientIdByUsername(username);

            int id = -1;
            while(idUsername.moveToNext()){
                id = idUsername.getInt(0);
            }
            if (id != -1) {
                LocalDate localDate = java.time.LocalDate.now();
                Cursor cursor = databaseHandler.checkAppointment(id, localDate);
                if (cursor.getCount() != 0 && sp_settings.getBoolean("send_notifications", false) == true) {
                    startService(new Intent(this, NotificationService.class));
                }
            }
        }
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
        //finish();
    }

}