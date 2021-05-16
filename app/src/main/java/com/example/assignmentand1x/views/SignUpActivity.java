package com.example.assignmentand1x.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.assignmentand1x.R;
import com.example.assignmentand1x.viewModel.SignUpViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import service.SignUpService;

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
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        SignUpService signUpService = new SignUpService(signUpViewModel);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //find views
        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        username = findViewById(R.id.editTextUsernameSignup);
        password = findViewById(R.id.editTextPasswordSignup);
        joinUsButton = findViewById(R.id.buttonJoinUs);
        //---------------------


        //------JOIN US--------
        joinUsButton.setOnClickListener(v -> {

            String usernameText = username.getText().toString();
            String passwordText = password.getText().toString();
            String firstNameText = firstName.getText().toString();
            String lastNameText = lastName.getText().toString();

            String validateString = signUpService.validateUserForm(usernameText, firstNameText, lastNameText, passwordText);
            if (validateString != null) {
                Snackbar.make(v, validateString, Snackbar.LENGTH_SHORT).show();
            } else {
                signUpViewModel.getAccount(usernameText).observe(this, account -> {
                    if (account != null) {
                        Snackbar.make(v, "Username already exists", Snackbar.LENGTH_SHORT).show();
                    } else {
                        signUpService.createUser(usernameText, firstNameText, lastNameText, passwordText);
                        startActivity(new Intent(this, LoginActivity.class));
                    }
                });
            }

        });
        //---------------------
    }
}