package com.example.assignmentand1x.signup;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.assignmentand1x.login.Login;

@Entity(tableName = "signUp_table",
        foreignKeys = @ForeignKey(entity = Login.class,
        parentColumns = "signup_username", childColumns = "login_username",
        onDelete = ForeignKey.CASCADE))
public class SignUp {

    private String firstName;
    private String lastName;
    @PrimaryKey
    @ColumnInfo(name = "signup_username")
    private String username;
    private String password;

    public SignUp(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
