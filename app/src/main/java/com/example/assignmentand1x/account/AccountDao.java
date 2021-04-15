package com.example.assignmentand1x.account;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.assignmentand1x.account.Account;

@Dao
public interface AccountDao {

    @Insert
    void insert(Account account);

   /* @Update
    void update(SignUp signUp);*/

    @Delete
    void deleteAccount(Account account);

    @Query("SELECT * FROM accounts WHERE username = :username")
    LiveData<Account> getAccount(String username);

}
