package com.example.assignmentand1x.signup;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.assignmentand1x.database.DogWithCoronaDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SignUpRepository {
    SignUpDao signUpDao;
    ExecutorService executorService;
    LiveData<SignUp> account;
    private static SignUpRepository instance;

    public SignUpRepository(Application application) {
        DogWithCoronaDatabase database = DogWithCoronaDatabase.getInstance(application);
        signUpDao = database.signUpDao();
        account = signUpDao.getAccount(); //??
        executorService = Executors.newFixedThreadPool(2);
    }
    public static synchronized SignUpRepository getInstance(Application application){
        if(instance==null){
            instance = new SignUpRepository(application);
        }
        return instance;
    }

    public void createAccount(SignUp signUp){
        executorService.execute(()-> signUpDao.createAccount(signUp));
    }

    public LiveData<SignUp> getAccount() {
        return account;
    }
    public void deleteAccount(SignUp signUp){
        executorService.execute(()-> signUpDao.delete(signUp));
    }
}
