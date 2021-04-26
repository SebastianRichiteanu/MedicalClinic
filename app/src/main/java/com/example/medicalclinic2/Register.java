package com.example.medicalclinic2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.medicalclinic2.model.DatabaseHandler;

public class Register extends AppCompatActivity {

    private Button register_button;
    private EditText register_username;
    private EditText register_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_button = (Button) findViewById(R.id.register_submit);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_username = (EditText) findViewById(R.id.register_username);
                register_password = (EditText) findViewById(R.id.register_password);
                Intent i = new Intent(Register.this, MainActivity.class);
                i.putExtra("username",register_username.getText().toString());
                i.putExtra("password",register_password.getText().toString());
                startActivity(i);
            }
        });

    }
}