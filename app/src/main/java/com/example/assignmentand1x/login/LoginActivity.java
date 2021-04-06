package com.example.assignmentand1x.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.assignmentand1x.R;
import com.example.assignmentand1x.signup.SignUpActivity;

public class LoginActivity extends AppCompatActivity {

    Button signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //-----SIGN UP------
        signUpButton = findViewById(R.id.buttonSignUp);
        signUpButton.setOnClickListener(v->{
            startActivity(new Intent(this, SignUpActivity.class));
        });
        //------------------
    }
}