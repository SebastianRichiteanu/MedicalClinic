package com.example.medicalclinic2;

import android.content.Intent;
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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        databaseHandler = new DatabaseHandler(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            System.out.println("AAAAAAAAA");
            String method = extras.getString("method", "");
            if (method.equals("Register")) {
                String username = extras.getString("username");
                String password = extras.getString("password");
                String role = extras.getString("role");
                databaseHandler.insertUser(username, password, role);
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
                    }
                    else
                        databaseHandler.insertDoctor(name, surname, age, address, phoneNo, specialization, username);
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
            MenuItem itemProfile = menu.findItem(R.id.profileview);
            itemProfile.setVisible(true);
        }
        else {
            MenuItem item = menu.findItem(R.id.login);
            item.setVisible(true);
            MenuItem items = menu.findItem(R.id.logout);
            items.setVisible(false);
            MenuItem itemReg = menu.findItem(R.id.register);
            itemReg.setVisible(true);
            MenuItem itemProfile = menu.findItem(R.id.profileview);
            itemProfile.setVisible(false);
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
        if (id == R.id.action_settings) {
            return true;
        }
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
        if (id == R.id.profileview) {
            Intent intent = new Intent(this, ProfileView.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}