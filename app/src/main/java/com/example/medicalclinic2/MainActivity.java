package com.example.medicalclinic2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import com.example.medicalclinic2.model.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

        databaseHandler.deleteAll();
        databaseHandler.insertUser("burtigus", "Abcdef1");
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            String username = extras.getString("username");
//            String password = extras.getString("password");
//            databaseHandler.insertUser(username,password);
//        }

        System.out.println("USERI!!!!");
        // afisare user
//        databaseHandler.insertUser("gigel", "GGL");
//        databaseHandler.insertUser("fratele lui gigel", "GGL2");
        Cursor cursor = databaseHandler.allDataUsers();

        if(cursor.getCount() == 0)
            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();
        else{
            while(cursor.moveToNext()){
                System.out.println("Id: " + cursor.getString(0));
                System.out.println("Username: " + cursor.getString(1));
                System.out.println("Password: " + cursor.getString(2));
            }
        }


        System.out.println(sp.getString("username","aaaaaa"));
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
        if (id == R.id.login){
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
        if (id == R.id.register) {
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        }
        if (id == R.id.logout) {
            sp.edit().putBoolean("logged", false).apply();
            // + username
        }

        return super.onOptionsItemSelected(item);
    }
}