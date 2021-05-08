package com.example.medicalclinic2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicalclinic2.model.DatabaseHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    private Button register_button;
    private EditText register_username;
    private EditText register_password;

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_button = (Button) findViewById(R.id.register_submit);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_username = (EditText) findViewById(R.id.register_username);
                register_password = (EditText) findViewById(R.id.register_password);

                boolean found = false;
                boolean goodPass = false;
                Cursor cursor = databaseHandler.searchUserByUsername(register_username.getText().toString());
                if(cursor.getCount() != 0){
                    System.out.println("EROAREEEEEEE");
                    register_username.setError("The username already exists!");
                    found = true;
                }

                if(register_password.getText().toString().length()<8 &&!isValidPassword(register_password.getText().toString())){
                    System.out.println("Not Valid");
                    register_password.setError("The password must contain at least one uppercase letter and at least one digit");
                }else{
                    System.out.println("Valid");
                    goodPass = true;
                }

                if (!found && goodPass) {
                    Intent i = new Intent(Register.this, MainActivity.class);
                    i.putExtra("username",register_username.getText().toString());
                    i.putExtra("password",register_password.getText().toString());
                    startActivity(i);
                }
            }
        });

    }
}