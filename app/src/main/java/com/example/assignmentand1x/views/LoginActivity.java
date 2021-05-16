package com.example.assignmentand1x.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.assignmentand1x.R;
import com.example.assignmentand1x.viewModel.LoginViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import service.LoginService;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;
    LoginViewModel viewModel;
    ProgressBar progressBar;
    LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginService = new LoginService(viewModel);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //find views
        progressBar = findViewById(R.id.progressBar);
        loginButton = findViewById(R.id.buttonLogin);
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        loginButton.setOnClickListener(this::onClick);
        //------------------
    }

    //login
    private void onClick(View v) {

        String usernameText = username.getText().toString();
        String passwordText = password.getText().toString();

        String validateString = loginService.validateUserForm(usernameText, passwordText);
        if (usernameText.equals("") || passwordText.equals("")) {
            Snackbar.make(v, validateString, Snackbar.LENGTH_SHORT).show();
        }
        viewModel.getAccount(usernameText).observe(this, account -> {
            if (account != null) {
                if (account.getPassword().equals(passwordText)) {

                    Intent intent = new Intent(this, MainPageActivity.class);
                    intent.putExtra("user", usernameText);
                    progressBar.setVisibility(View.VISIBLE);
                    startActivity(intent);
                } else {
                    Snackbar.make(v, "Password is not correct", Snackbar.LENGTH_SHORT).show();
                }
            } else {
                Snackbar.make(v, "Username does not exist", Snackbar.LENGTH_SHORT).show();
            }
        });

        viewModel.getAccount(username.getText().toString()).observe(this, UserContext::login);

    }
    //---------------
}