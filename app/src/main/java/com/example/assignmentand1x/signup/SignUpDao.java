package com.example.assignmentand1x.signup;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SignUpDao {

    @Insert
    void createAccount(SignUp signUp);

   /* @Update
    void update(SignUp signUp);*/

    @Delete
    void delete(SignUp signUp);

    @Query("SELECT * FROM signUp_table WHERE username = :username2")
    SignUp getAccount(String username2);
}
