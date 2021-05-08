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

public class Register extends AppCompatActivity {

    private Button register_button;
    private EditText register_username;
    private EditText register_password;

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

                boolean found = false;
                Cursor cursor = databaseHandler.allDataUsers();
                if(cursor.getCount() == 0)
                    Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();
                else{
                    while(cursor.moveToNext()){
                        if (cursor.getString(1).equals(register_username.getText().toString())) {
                            System.out.println("EROAREEEEEEE");
                            found = true;
                        }
                    }
                }
                if (!found) {
                    register_password = (EditText) findViewById(R.id.register_password);
                    Intent i = new Intent(Register.this, MainActivity.class);
                    i.putExtra("username",register_username.getText().toString());
                    i.putExtra("password",register_password.getText().toString());
                    startActivity(i);
                }
            }
        });

    }
}