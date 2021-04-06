package com.example.assignmentand1x.login;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.assignmentand1x.signup.SignUp;

@Entity(tableName = "login_table")
public class Login {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "login_username")
    private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
