package com.example.medicalclinic2;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_button = (Button) findViewById(R.id.login_submit);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_username = (EditText) findViewById(R.id.login_username);
                login_password = (EditText) findViewById(R.id.login_password);

                boolean goodUsername = false;
                boolean goodPass = false;
                System.out.println("Inainte!!!!!!!!!!");
                Cursor cursor = databaseHandler.searchUserByUsername(login_username.getText().toString());
                System.out.println("DUPA!!!!!!!!!!!!");
                if(cursor.getCount() == 0){
                    login_username.setError("The username doesn't exist!");
                }
                else{
                    goodUsername = true;
                }

                if (goodUsername) {
//                    Cursor cursor1 = databaseHandler.searchPassword(login_username.getText().toString());
                    System.out.println("??????????????");
                    String userPass = "";
                    while(cursor.moveToNext()){
                        userPass = cursor.getString(2);
                    }
//                    System.out.println(cursor.getString(2));
//                    System.out.println("!!!!!!!!!!!!!!!!!!!!!");
                    if (userPass.equals(login_password.getText().toString())) {
                        goodPass = true;
                    } else {
                        login_password.setError("Invalid password");
                    }
                }

                if (goodUsername && goodPass) {
                    Intent i = new Intent(Login.this, MainActivity.class);
//                    i.putExtra("username",reg_username.getText().toString());
//                    i.putExtra("password",register_password.getText().toString());
                    startActivity(i);
                }
            }
        });

    }
}