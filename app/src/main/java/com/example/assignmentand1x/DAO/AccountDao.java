package com.example.assignmentand1x.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignmentand1x.model.Account;

@Dao
public interface AccountDao {

    @Insert
    void insert(Account account);

    @Update
    void update(Account account);

    @Delete
    void deleteAccount(Account account);

    @Query("SELECT * FROM accounts WHERE username = :username")
    LiveData<Account> getAccount(String username);

}
