package com.example.assignmentand1x.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.assignmentand1x.R;

public class WelcomeActivity extends AppCompatActivity {

    Button buttonLogin;
    Button buttonJoinUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //Login button
        buttonLogin = findViewById(R.id.buttonLoginWelcome);
        buttonLogin.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        });

        //Join us button
        buttonJoinUs = findViewById(R.id.buttonJoinWelcome);
        buttonJoinUs.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
        });
    }
}