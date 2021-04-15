package com.example.assignmentand1x.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.assignmentand1x.R;
import com.example.assignmentand1x.account.Account;
import com.example.assignmentand1x.login.LoginActivity;
import com.google.android.material.snackbar.Snackbar;

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
        firstName =(EditText) findViewById(R.id.editTextFirstName);
        lastName = (EditText)findViewById(R.id.editTextLastName);
        username = (EditText)findViewById(R.id.editTextUsernameSignup);
        password = (EditText)findViewById(R.id.editTextPasswordSignup);
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        //---------------------

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //------JOIN US--------
        joinUsButton = (Button)findViewById(R.id.buttonJoinUs);
        joinUsButton.setOnClickListener(v->{

           /* if(signUpViewModel.ifExists(username.getText().toString()))
                Snackbar.make(v,"username already exists", Snackbar.LENGTH_SHORT).show();
            }*/
             if(username.getText().toString().equals("") || firstName.getText().toString().equals("") || lastName.getText().toString().equals("")
            || password.getText().toString().equals("")){
                Snackbar.make(v, "First name, last name, username and password cannot be empty", Snackbar.LENGTH_SHORT).show();
            }
            else if(password.getText().length()<5){
                Snackbar.make(v,"Password has to contain at least 5 signs", Snackbar.LENGTH_SHORT).show();
            }
            else
            {
                signUpViewModel.createAccount(new Account(firstName.getText().toString(),lastName.getText().toString(),
                        username.getText().toString(), password.getText().toString()));
                startActivity(new Intent(this, LoginActivity.class));

            }

        });
        //---------------------
    }
}