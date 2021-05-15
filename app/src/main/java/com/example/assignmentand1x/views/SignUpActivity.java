package com.example.assignmentand1x.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.assignmentand1x.model.Account;
import com.example.assignmentand1x.R;
import com.example.assignmentand1x.viewModel.SignUpViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

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

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //--------------------
        firstName =  findViewById(R.id.editTextFirstName);
        lastName =  findViewById(R.id.editTextLastName);
        username =  findViewById(R.id.editTextUsernameSignup);
        password =  findViewById(R.id.editTextPasswordSignup);
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        //---------------------

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //------JOIN US--------
        joinUsButton =  findViewById(R.id.buttonJoinUs);
        joinUsButton.setOnClickListener(v -> {

            String usernameText = username.getText().toString();
            String passwordText = password.getText().toString();
            String firstNameText = firstName.getText().toString();
            String lastNameText = lastName.getText().toString();

            if (usernameText.equals("") || firstNameText.equals("") || lastNameText.equals("")
                    || passwordText.equals("")) {
                Snackbar.make(v, "First name, last name, username and password cannot be empty", Snackbar.LENGTH_SHORT).show();
            } else if (password.getText().length() < 5) {
                Snackbar.make(v, "Password has to contain at least 5 signs", Snackbar.LENGTH_SHORT).show();
            } else {
                signUpViewModel.getAccount(usernameText).observe(this, account -> {
                    if (account!=null) {
                        Snackbar.make(v, "Username already exists", Snackbar.LENGTH_SHORT).show();
                    } else {
                        signUpViewModel.createAccount(new Account(firstNameText, lastNameText,
                                usernameText, passwordText));
                        startActivity(new Intent(this, LoginActivity.class));
                    }
                });


            }

        });
        //---------------------
    }
}