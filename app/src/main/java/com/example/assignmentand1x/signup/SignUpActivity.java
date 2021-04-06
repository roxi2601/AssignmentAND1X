package com.example.assignmentand1x.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.assignmentand1x.R;
import com.example.assignmentand1x.login.LoginActivity;

public class SignUpActivity extends AppCompatActivity {

    private SignUpViewModel signUpViewModel;

    EditText firstName;
    EditText lastName;
    EditText username;
    EditText password;
    Button joinUsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //--------------------
        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        username = findViewById(R.id.editTextUsernameSignup);
        password = findViewById(R.id.editTextPasswordSignup);
        //---------------------

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //------JOIN US--------
        joinUsButton = findViewById(R.id.buttonJoinUs);
        joinUsButton.setOnClickListener(v->{

            startActivity(new Intent(this, LoginActivity.class));
            signUpViewModel.createAccount(new SignUp(firstName.getText().toString(),lastName.getText().toString(),
                    username.getText().toString(), password.getText().toString()));
        });
        //---------------------
    }
}