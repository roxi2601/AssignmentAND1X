package com.example.assignmentand1x.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignmentand1x.model.Account;
import com.example.assignmentand1x.R;
import com.example.assignmentand1x.views.UserContext;
import com.example.assignmentand1x.viewModel.LoginViewModel;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText username ;
    EditText password;
    Button signUpButton;
    Button loginButton;
    LoginViewModel viewModel;
    Button googleButton;

    // FIREBASE
    private final static int RC_SIGN_IN = 42;
    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FirebaseApp.initializeApp(this);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        checkIfSignedIn();
        setContentView(R.layout.activity_login);
        //-----SIGN UP------
        signUpButton = findViewById(R.id.buttonSignUp);
        signUpButton.setOnClickListener(v -> {

            startActivity(new Intent(this, SignUpActivity.class));
        });
        //------------------

        //-----LOGIN--------
        loginButton = findViewById(R.id.buttonLogin);
        googleButton = findViewById(R.id.buttonGoogle);
        googleButton.setOnClickListener(this::signIn);
        username = findViewById(R.id.editTextUsername);
        username.setText("ooooo");
        password = findViewById(R.id.editTextPassword);
        password.setText("ooooo");
        loginButton.setOnClickListener(this::onClick);
        //------------------
    }


    private void onClick(View v) {

        String usernameText = username.getText().toString();
        String passwordText = password.getText().toString();

        if (usernameText.equals("") || passwordText.equals("")) {
            Snackbar.make(v, "Username and password cannot be empty", Snackbar.LENGTH_SHORT).show();
        }
        viewModel.getAccount(usernameText).observe(this, account -> {
            if (account != null) {
                if (account.getPassword().equals(passwordText)) {

                    Intent intent = new Intent(this, MainPageActivity.class);
                     //intent.putExtra("user",usernameText);
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

    //----------------FOR FIREBASE--------------------------------------
    private void checkIfSignedIn() {

        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null)
                goToMainActivity();
        });
    }

    private void goToMainActivity() {
        startActivity(new Intent(this, MainPageActivity.class));
        finish();
    }
    public void signIn(View v) {

        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.logo)
                .build();

        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            handleSignInRequest(resultCode);
        }
    }

    private void handleSignInRequest(int resultCode) {
        if (resultCode == RESULT_OK)
            goToMainActivity();
        else
            Toast.makeText(this, "SIGN IN CANCELLED", Toast.LENGTH_SHORT).show();
    }
    //----------------FOR FIREBASE-END--------------------------------------
}