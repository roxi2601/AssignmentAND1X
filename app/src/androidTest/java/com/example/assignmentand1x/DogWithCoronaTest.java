package com.example.assignmentand1x;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import static org.mockito.Mockito.mock;
import com.example.assignmentand1x.DAO.AccountDao;
import com.example.assignmentand1x.DAO.OfferDao;
import com.example.assignmentand1x.database.DogWithCoronaDatabase;
import com.example.assignmentand1x.model.Account;
import com.example.assignmentand1x.repository.AccountRepository;
import com.example.assignmentand1x.viewModel.SignUpViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DogWithCoronaTest {
    DogWithCoronaDatabase database;
    SignUpViewModel signUpViewModel;
    AccountRepository accountRepository;
    ExecutorService executorService;
    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        database= Room.inMemoryDatabaseBuilder(context, DogWithCoronaDatabase.class).build();
        accountRepository = AccountRepository.getInstance(ApplicationProvider.getApplicationContext());
        executorService = Executors.newFixedThreadPool(2);
    }


    @Test
    public void signUp_isCorrect() {
        String username = "bob123";

        Account account = new Account("Bob", "Johnson", username, "12345");
        executorService.execute(()-> accountRepository.insert(account));

        /*accountRepository.getAccount(username).observe(this, user->{

        });*/
        assertEquals(username, accountRepository.getAccount(username).getValue());
    }
    @Test
    public void login_isCorrect(){

    }
    @Test
    public void addNewOffer_isCorrect(){

    }
    @Test
    public void editOffer_isCorrect(){

    }
    @Test
    public void deleteOffer_isCorrect(){

    }
    @Test
    public void getOffer_isCorrect(){

    }
    @Test
    public void getAccount_isCorrect() {
        assertEquals("domi123", (accountRepository.getAccount("domi123")));
    }
    @Test
    public void getAllOffers_isCorrect(){

    }
}
 class LiveDataTestUtil {

    /**
     * Get the value from a LiveData object. We're waiting for LiveData to emit, for 2 seconds.
     * Once we got a notification via onChanged, we stop observing.
     */
    public static <T> T getValue(final LiveData<T> liveData) throws InterruptedException {
        final Object[] data = new Object[1];
        final CountDownLatch latch = new CountDownLatch(1);
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(@Nullable T o) {
                data[0] = o;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };
        liveData.observeForever(observer);
        latch.await(2, TimeUnit.SECONDS);
        //noinspection unchecked
        return (T) data[0];
    }
}