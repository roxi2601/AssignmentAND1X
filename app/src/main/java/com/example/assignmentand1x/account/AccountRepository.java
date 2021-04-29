package com.example.assignmentand1x.account;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.assignmentand1x.database.DogWithCoronaDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountRepository {
    AccountDao accountDao;

    ExecutorService executorService;
    private static AccountRepository instance;
    private final MutableLiveData<Boolean> ifExistsLiveData = new MutableLiveData<>();

    public LiveData<Boolean> ifExists(String username){
        executorService.execute(()-> ifExistsLiveData.postValue(accountDao.getAccount(username) != null));
        return ifExistsLiveData;
    }

    private AccountRepository(Application application){

        DogWithCoronaDatabase database = DogWithCoronaDatabase.getInstance(application);
        accountDao = database.accountDao();
        executorService = Executors.newFixedThreadPool(2);
    }


    public static synchronized AccountRepository getInstance(Application application){
        if(instance==null){
            instance = new AccountRepository(application);
        }
        return instance;
    }

   public Account getAccount(String username){
        return  accountDao.getAccount(username);
   }
    public void insert(Account account){
        executorService.execute(()-> accountDao.insert(account));
    }
    public void deleteAccount(Account account){
        executorService.execute(()-> accountDao.deleteAccount(account));
    }
}