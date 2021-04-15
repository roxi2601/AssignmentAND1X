package com.example.assignmentand1x.signup;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.assignmentand1x.account.Account;
import com.example.assignmentand1x.account.AccountRepository;

public class SignUpViewModel extends AndroidViewModel {

    private final AccountRepository repository;

    public SignUpViewModel(Application app){
        super(app);
        repository = AccountRepository.getInstance(app);
    }

    public void createAccount (final Account account){
        System.out.println(account);
        repository.insert(account);
    }
    public boolean ifExists(String username){
        return repository.ifExists(username);
    }
  public LiveData<Account> getAccount(String username){
        return repository.getAccount(username);
    }
    public void deleteAccount(final Account account){
        repository.deleteAccount(account);
    }
}
