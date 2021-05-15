package com.example.assignmentand1x.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.assignmentand1x.model.Account;
import com.example.assignmentand1x.repository.AccountRepository;

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
    public LiveData<Account> getAccount(String username){
        return repository.getAccount(username);
    }
}
