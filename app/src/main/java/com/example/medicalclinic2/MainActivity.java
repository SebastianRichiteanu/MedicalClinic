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

import java.sql.Date;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public DatabaseHandler databaseHandler;

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

        databaseHandler = new DatabaseHandler(this);
        
        /**
        databaseHandler.deleteAll();

        System.out.println("USERI!!!!");
        // afisare user
        databaseHandler.deleteAll();
        databaseHandler.insertUser("gigel", "GGL");
        databaseHandler.insertUser("fratele lui gigel", "GGL2");
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


        System.out.println("DOCTORI!!!!");
        databaseHandler.insertDoctor("prenume","nume",25,"str.sforii","0723456789",123.2,"specializare");

        Cursor cursor3 = databaseHandler.allDataDoctors();
        if(cursor3.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();}
        else{
            while(cursor3.moveToNext()){
                System.out.println("Id: " + cursor3.getString(0));
                System.out.println("Name: " + cursor3.getString(1));
                System.out.println("Surname: " + cursor3.getString(2));
                System.out.println("Age: " + cursor3.getString(3));
                System.out.println("Address: " + cursor3.getString(4));
                System.out.println("Phone: " + cursor3.getString(5));
                System.out.println("Salary: " + cursor3.getString(6));
                System.out.println("Specialization: " + cursor3.getString(7));

            }
        }

        System.out.println("SUPPLIERI!!!!");
        databaseHandler.insertSupplier("name","location");

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


        System.out.println("PACIENTI!!!!");
        databaseHandler.insertPatient("necula", "narcis", 20, "aa", "07", "frumusete");
        Cursor cursor4 = databaseHandler.allDataPatients();

        if(cursor4.getCount() == 0)
            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();
        else{
            while(cursor4.moveToNext()){
                System.out.println("Id: " + cursor4.getString(0));
                System.out.println("name: " + cursor4.getString(1));
                System.out.println("surname: " + cursor4.getString(2));
                System.out.println("age: " + cursor4.getString(3));
                System.out.println("address:" + cursor4.getString(4));
                System.out.println("phone:" + cursor4.getString(5));
                System.out.println("condition:" + cursor4.getString(6));
            }
        }

        System.out.println("MEDPRESC!!!!");
        databaseHandler.insertMedPresc(0,0);
        Cursor cursor5 = databaseHandler.allDataMedPrescs();

        if(cursor5.getCount() == 0)
            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();
        else{
            while(cursor5.moveToNext()){
                System.out.println("Id Medication: " + cursor5.getString(0));
                System.out.println("Id Prescription: " + cursor5.getString(1));
            }
        }


        System.out.println("PRESCRIPTION!!!!");
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        databaseHandler.insertPrescription(date);
        Cursor cursor6 = databaseHandler.allDataPrescription();

        if(cursor6.getCount() == 0)
            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();
        else{
            while(cursor6.moveToNext()){
                System.out.println("Id: " + cursor6.getString(0));
                System.out.println("Date: " + cursor6.getString(1));
            }
        }

        System.out.println("APPOINTMENTS!!!!");
        databaseHandler.insertAppointment(0,0,date);
        Cursor cursor7 = databaseHandler.allDataAppointments();

        if(cursor7.getCount() == 0)
            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();
        else{
            while(cursor7.moveToNext()){
                System.out.println("Id: " + cursor7.getString(0));
                System.out.println("IdDoc: " + cursor7.getString(1));
                System.out.println("IdPatient: " + cursor7.getString(2));
                System.out.println("Date: " + cursor7.getString(3));
            }
        }


        System.out.println("MEDICATION!!!!");
        databaseHandler.insertMedication("nume",123.2,0);
        Cursor cursor8 = databaseHandler.allDataMedication();

        if(cursor8.getCount() == 0)
            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();
        else{
            while(cursor8.moveToNext()){
                System.out.println("Id: " + cursor8.getString(0));
                System.out.println("Name: " + cursor8.getString(1));
                System.out.println("Price: " + cursor8.getString(2));
                System.out.println("SupplierID: " + cursor8.getString(3));
            }
        }

        **/

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String username = extras.getString("username");
            String password = extras.getString("password");
            databaseHandler.insertUser(username,password);
        }

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