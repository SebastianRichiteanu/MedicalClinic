package com.example.medicalclinic2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalclinic2.model.DatabaseHandler;

public class Login extends AppCompatActivity {
    private Button login_button;
    private EditText login_username;
    private EditText login_password;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_button = (Button) findViewById(R.id.login_submit);

        sp = getSharedPreferences("login", MODE_PRIVATE);

        if (sp.getBoolean("logged", false)) {
            goToMainActivity();
        }


        login_username = (EditText) findViewById(R.id.login_username);
        login_password = (EditText) findViewById(R.id.login_password);
        login_username.setText(sp.getString("username", ""));

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean goodUsername = false;
                boolean goodPass = false;

                Cursor cursor = databaseHandler.searchUserByUsername(login_username.getText().toString());

                if (cursor.getCount() == 0) {
                    login_username.setError("The username doesn't exist!");
                } else {
                    goodUsername = true;
                }
                String role = "";
                if (goodUsername) {
                    String userPass = "";

                    while (cursor.moveToNext()) {
                        userPass = cursor.getString(2);
                        role = cursor.getString(3);
                    }

                    if (userPass.equals(login_password.getText().toString())) {
                        goodPass = true;
                    } else {
                        login_password.setError("Invalid password");
                    }
                }

                if (goodUsername && goodPass) {
                    goToMainActivity();
                    sp.edit().putBoolean("logged", true).apply();
                    sp.edit().putString("username", login_username.getText().toString()).apply();
                    sp.edit().putString("role", role).apply();
                }
            }
        });
    }

    public void goToMainActivity() {
        Intent i = new Intent(Login.this, MainActivity.class);
        startActivity(i);
    }
}

