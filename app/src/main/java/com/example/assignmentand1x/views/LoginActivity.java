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
    Account currentUser;
/*
    // FIREBASE
    Button googleButton;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    //

    // FIREBASE
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();

        if(user!=null){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }
    //*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

      /*  //----FIREBASE------
        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();
        createRequest();
        googleButton = findViewById(R.id.buttonGoogle);
        googleButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        signIn();
                    }
                }
        );
        //-----------------*/

        //-----SIGN UP------
        signUpButton = findViewById(R.id.buttonSignUp);
        signUpButton.setOnClickListener(v -> {

            startActivity(new Intent(this, SignUpActivity.class));
        });
        //------------------

        //-----LOGIN--------
        loginButton = findViewById(R.id.buttonLogin);
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
/*
    //----------------FOR FIREBASE--------------------------------------
    private void createRequest() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //Build a GoogleSignInClient with the options specified by gso
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account =  task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(),MainPageActivity.class);
                            startActivity(intent);


                        } else {
                            Toast.makeText(LoginActivity.this, "Sorry auth failed.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
    //----------------FOR FIREBASE-END--------------------------------------*/
}