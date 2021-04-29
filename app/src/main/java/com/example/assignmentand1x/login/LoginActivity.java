package com.example.assignmentand1x.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.assignmentand1x.MainPageActivity;
import com.example.assignmentand1x.R;
import com.example.assignmentand1x.account.Account;
import com.example.assignmentand1x.signup.SignUpActivity;
import com.example.assignmentand1x.signup.SignUpViewModel;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button signUpButton;
    Button loginButton;
    LoginViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        //-----SIGN UP------
        signUpButton = findViewById(R.id.buttonSignUp);
        signUpButton.setOnClickListener(v->{
            startActivity(new Intent(this, SignUpActivity.class));
        });
        //------------------

        //-----LOGIN--------
        loginButton = findViewById(R.id.buttonLogin);
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        loginButton.setOnClickListener(this::onClick);
        //------------------

    }

    private void onClick(View v) {
        if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
            Snackbar.make(v, "Username and password cannot be empty", Snackbar.LENGTH_SHORT).show();
        } else if (viewModel.ifExists(username.getText().toString())){

            Account accountFromDB =viewModel.getAccount(username.getText().toString());
                  if(accountFromDB.getPassword().equals(password.getText().toString()))
                  {
                      startActivity(new Intent(this, MainPageActivity.class));
                  }
                  else
                  {
                      Snackbar.make(v, "Password is not correct", Snackbar.LENGTH_SHORT).show();
                  }
        }
        else
        {
            Snackbar.make(v, "Username does not exist", Snackbar.LENGTH_SHORT).show();
        }
    }
}