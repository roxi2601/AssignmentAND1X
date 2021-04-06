package com.example.assignmentand1x.signup;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SignUpViewModel extends AndroidViewModel {

    private final SignUpRepository repository;

    public SignUpViewModel(Application app){
        super(app);
        repository = SignUpRepository.getInstance(app);
    }

    public LiveData<SignUp> getAccount(){
        return repository.getAccount();
    }
    public void createAccount (final SignUp signUp){
        repository.createAccount(signUp);
    }
    public void deleteAccount(final SignUp signUp){
        repository.deleteAccount(signUp);
    }
}
