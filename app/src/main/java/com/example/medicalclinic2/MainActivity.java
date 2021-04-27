package com.example.medicalclinic2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.medicalclinic2.model.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DatabaseHandler databaseHandler = new DatabaseHandler(this);

        //afisare users
        Cursor cursor = databaseHandler.allData();
        if(cursor.getCount() == 0)
            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();
        else{
            while(cursor.moveToNext()){
//                Toast.makeText(getApplicationContext(), "Username: "+cursor.getString(1), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "Password: "+cursor.getString(2), Toast.LENGTH_SHORT).show();
                    System.out.println("Id: " + cursor.getString(0));
                  System.out.println("Username: " + cursor.getString(1));
                  System.out.println("Password: " + cursor.getString(2));
            }
        }

        databaseHandler.deleteAllSupplier();
        databaseHandler.insertSupplier("name","location");

        //afisare suppliers
        Cursor cursor2 = databaseHandler.allDataSuppliers();
        if(cursor2.getCount() == 0)
            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();
        else{
            while(cursor2.moveToNext()){
//                Toast.makeText(getApplicationContext(), "Username: "+cursor.getString(1), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "Password: "+cursor.getString(2), Toast.LENGTH_SHORT).show();
                System.out.println("Id: " + cursor2.getString(0));
                System.out.println("Name: " + cursor2.getString(1));
                System.out.println("Location: " + cursor2.getString(2));
            }
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String username = extras.getString("username");
            String password = extras.getString("password");
            databaseHandler.insertUser(username,password);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        if (id == R.id.register) {
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}