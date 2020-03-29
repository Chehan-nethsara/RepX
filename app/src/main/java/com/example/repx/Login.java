package com.example.repx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
    Button SigninButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SigninButton = findViewById(R.id.Sign_up);
        SigninButton.setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {
               startActivity(new Intent(Login.this, Signup.class ));
            }
        }));

    }
}
