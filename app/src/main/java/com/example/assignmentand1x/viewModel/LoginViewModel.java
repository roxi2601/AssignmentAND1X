package com.example.assignmentand1x.viewModel;
import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.assignmentand1x.firebase.UserRepository;
import com.example.assignmentand1x.model.Account;
import com.example.assignmentand1x.repository.AccountRepository;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {
    private final AccountRepository repository;
    private final UserRepository userRepository;
    public LoginViewModel(Application app){
        super(app);
        repository = AccountRepository.getInstance(app);
        userRepository = UserRepository.getInstance(app);
    }

    public LiveData<Account> getAccount(String username){
        return repository.getAccount(username);
    }
}
