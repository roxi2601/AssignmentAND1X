package com.example.assignmentand1x.repository;
import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.assignmentand1x.model.Account;
import com.example.assignmentand1x.DAO.AccountDao;
import com.example.assignmentand1x.database.DogWithCoronaDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountRepository {
    AccountDao accountDao;
    ExecutorService executorService;
    private static AccountRepository instance;
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

    public LiveData<Account> getAccount(String username){
        return  accountDao.getAccount(username);
    }

    public void insert(Account account){
        executorService.execute(()-> accountDao.insert(account));
    }
    public void update(Account account){
        executorService.execute(()-> accountDao.update(account));
    }
    public void deleteAccount(Account account){
        executorService.execute(()-> accountDao.deleteAccount(account));
    }
}