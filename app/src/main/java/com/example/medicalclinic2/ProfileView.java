package com.example.medicalclinic2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.medicalclinic2.model.DatabaseHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileView extends AppCompatActivity {

    private TextView profileview_username;
    private EditText profileview_name;
    private EditText profileview_surname;
    private EditText profileview_age;
    private EditText profileview_address;
    private EditText profileview_phoneNo;
    private EditText profileview_condition;
    private EditText profileview_specialization;
    private Button profileview_submit;
    public SharedPreferences sp;

    public static boolean isValidName(final String name) {
        Pattern pattern;
        Matcher matcher;

        int len = name.length();

        final String NAME_PATTERN = "^^([A-Z][-,a-z]+)";
        pattern = Pattern.compile(NAME_PATTERN);
        matcher = pattern.matcher(name);

        return (len >= 3 && matcher.matches());
    }

    public static boolean isValidSurname(final String surname) {
        Pattern pattern;
        Matcher matcher;

        int len = surname.length();
        System.out.println("Surname" + len);

        final String SURNAME_PATTERN = "^([A-Z][-,a-z]+)";
        pattern = Pattern.compile(SURNAME_PATTERN);
        matcher = pattern.matcher(surname);

        return (len >= 3 && matcher.matches());
    }

    public static boolean isValidAddress(final String address) {
        Pattern pattern;
        Matcher matcher;

        int len = address.length();

        final String ADDRESS_PATTERN = "^.{1,}";
        pattern = Pattern.compile(ADDRESS_PATTERN);
        matcher = pattern.matcher(address);

        return matcher.matches();
    }

    public static boolean isValidPhone(final String phone) {
        Pattern pattern;
        Matcher matcher;

        int len = phone.length();

        final String PHONE_PATTERN = "^[0]{1}[0-9]{9}$";
        pattern = Pattern.compile(PHONE_PATTERN);
        matcher = pattern.matcher(phone);

        return matcher.matches();
    }

    public static boolean isValidAge(final int age) {
        return (0 < age && age <= 99);
    }

    public static boolean isValidCondition(final String condition) {
        Pattern pattern;
        Matcher matcher;

        int len = condition.length();

        final String CONDITION_PATTERN = "^.{1,}";
        pattern = Pattern.compile(CONDITION_PATTERN);
        matcher = pattern.matcher(condition);

        return (len >= 5 && matcher.matches());
    }

    public static boolean isValidSpecialization(final String specialization) {
        Pattern pattern;
        Matcher matcher;

        int len = specialization.length();

        final String SPECIALIZATION_PATTERN = "^.{1,}";
        pattern = Pattern.compile(SPECIALIZATION_PATTERN);
        matcher = pattern.matcher(specialization);

        return (len >= 5 && matcher.matches());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        setContentView(R.layout.activity_profile_view);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        profileview_username = (TextView) findViewById(R.id.profileview_username);
        profileview_name = (EditText) findViewById(R.id.profileview_name);
        profileview_surname = (EditText) findViewById(R.id.profileview_surname);
        profileview_age = (EditText) findViewById(R.id.profileview_age);
        profileview_address = (EditText) findViewById(R.id.profileview_address);
        profileview_phoneNo = (EditText) findViewById(R.id.profileview_phoneNo);
        profileview_condition = (EditText) findViewById(R.id.profileview_condition);
        profileview_specialization = (EditText) findViewById(R.id.profileview_specialization);
        profileview_submit = (Button) findViewById(R.id.profileview_submit);

        String hello = "Hello, " + sp.getString("username","You are not logged in");
        profileview_username.setText(hello);
        String role = sp.getString("role","");


        if (role.equals("Patient")) {
            profileview_condition.setVisibility(View.VISIBLE);
            profileview_specialization.setVisibility(View.INVISIBLE);
            Cursor cursorPatient = databaseHandler.searchUserInPatients(sp.getString("username",""));
            while (cursorPatient.moveToNext()) {
                profileview_name.setText(cursorPatient.getString(1));
                profileview_surname.setText(cursorPatient.getString(2));
                profileview_age.setText(cursorPatient.getString(3));
                profileview_address.setText(cursorPatient.getString(4));
                profileview_phoneNo.setText(cursorPatient.getString(5));
                profileview_condition.setText(cursorPatient.getString(6));
            }
        }
        else if (role.equals("Doctor")) {
            profileview_condition.setVisibility(View.INVISIBLE);
            profileview_specialization.setVisibility(View.VISIBLE);
            Cursor cursorDoctor = databaseHandler.searchUserInDoctors(sp.getString("username",""));
            while (cursorDoctor.moveToNext()) {
                profileview_name.setText(cursorDoctor.getString(1));
                profileview_surname.setText(cursorDoctor.getString(2));
                profileview_age.setText(cursorDoctor.getString(3));
                profileview_address.setText(cursorDoctor.getString(4));
                profileview_phoneNo.setText(cursorDoctor.getString(5));
                profileview_specialization.setText(cursorDoctor.getString(6));
            }
        }

        profileview_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileView.this, MainActivity.class);
                String name = profileview_name.getText().toString();
                String surname = profileview_surname.getText().toString();
                int age = Integer.parseInt(profileview_age.getText().toString());
                String address = profileview_address.getText().toString();
                String phoneNo = profileview_phoneNo.getText().toString();
                String condition = profileview_condition.getText().toString();
                String specialization = profileview_specialization.getText().toString();

                boolean goodName = false;
                boolean goodSurname = false;
                boolean goodAge = false;
                boolean goodAddress = false;
                boolean goodPhone = false;
                boolean goodCondition = false;
                boolean goodSpecialization = false;

                if ( !isValidName(name) ) {
                    profileview_name.setError("The name must have at least 5 characters! The first one should be uppercase!");
                }
                else {
                    System.out.println("Nume valid");
                    goodName = true;
                }

                if ( !isValidSurname(surname) ) {
                    profileview_surname.setError("The surname must have at least 5 characters! The first one should be uppercase!");
                }
                else {
                    System.out.println("Surnume valid");
                    goodSurname = true;
                }

                if ( !isValidAge(age) ) {
                    profileview_age.setError("The age must be between 0 and 99!");
                }
                else {
                    System.out.println("Age valid");
                    goodAge = true;
                }

                if ( !isValidAddress(address) ) {
                    profileview_address.setError("The address must not be empty!");
                }
                else {
                    System.out.println("Address valid");
                    goodAddress = true;
                }

                if ( !isValidPhone(phoneNo) ) {
                    profileview_phoneNo.setError("The phone number must have exactly 10 digits, and start with 0!");
                }
                else {
                    System.out.println("Phone valid");
                    goodPhone = true;
                }

                if (role.equals("Patient")) {
                    if ( !isValidCondition(condition) ) {
                        profileview_condition.setError("The condition must have at least 5 characters!");
                    }
                    else {
                        System.out.println("Condition valid");
                        goodCondition = true;
                    }
                }
                else if (role.equals("Doctor")) {
                    if ( !isValidSpecialization(specialization) ) {
                        profileview_specialization.setError("The specialization must have at least 5 characters!");
                    }
                    else {
                        System.out.println("Specialization valid");
                        goodSpecialization = true;
                    }
                }

                if( goodName && goodSurname && goodAge && goodAddress && goodPhone && (goodCondition || goodSpecialization) ) {
                    i.putExtra("name", name);
                    i.putExtra("surname", surname);
                    i.putExtra("age", age);
                    i.putExtra("address", address);
                    i.putExtra("phoneNo", phoneNo);

                    if (role.equals("Patient")) {
                        i.putExtra("condition", condition);
                    }
                    else if (role.equals("Doctor")) {
                        i.putExtra("specialization", specialization);
                    }


                    i.putExtra("method", "profileview");
                    startActivity(i);
                }
            }
        });

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