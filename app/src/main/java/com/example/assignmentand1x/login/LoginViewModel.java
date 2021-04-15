package com.example.assignmentand1x.login;
import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.assignmentand1x.account.Account;
import com.example.assignmentand1x.account.AccountRepository;

public class LoginViewModel extends AndroidViewModel {
    private final AccountRepository repository;

    public LoginViewModel(Application app){
        super(app);
        repository = AccountRepository.getInstance(app);
    }

    public boolean ifExists(String username){
        return repository.ifExists(username);
    }
    public LiveData<Account> getAccount(String username){
        return repository.getAccount(username);
    }
}
