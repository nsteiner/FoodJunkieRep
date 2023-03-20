package com.example.foodjunkie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private EditText eName;
    private EditText ePassword;
    private Button eLogin;
    private TextView eAttemptsInfo;
    boolean isValid = false;

    private int counter = 5;

    private TextView eRegister;


    DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);
        eAttemptsInfo = findViewById(R.id.tvAttemptsInfo);
        eRegister = findViewById(R.id.tvRegister);

        //database code
        dataBaseHelper = new DataBaseHelper(LoginActivity.this);
        try {
            dataBaseHelper.createDataBase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if(inputName.isEmpty() || inputPassword.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please ensure user and password is entered", Toast.LENGTH_SHORT).show();
                } else{

                    isValid = validate(inputName, inputPassword);

                    if(!isValid){
                        counter--;
                        Toast.makeText(LoginActivity.this, "The username/password entered is incorrect", Toast.LENGTH_SHORT).show();
                        eAttemptsInfo.setText("Number of Attempts remaining: "  + counter);

                        if(counter == 0){
                            eLogin.setEnabled(false);
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                        //This switches the screen to homepage can be edited to open first screen of app.
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        //startActivityFromFragment(HomeFragment, intent);

                    }
                }
            }
        });


    }


    private boolean validate(String name, String password) {

        if (RegisterActivity.credentials != null) {
            if (name.equals(RegisterActivity.credentials.getUsername()) && password.equals(RegisterActivity.credentials.getPassword())) {
                return true;
            }
        }
        return false;
    }
}