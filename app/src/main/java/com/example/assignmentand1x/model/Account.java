package com.example.assignmentand1x.model;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


/*  (tableName = "signUp_table",
  foreignKeys = @ForeignKey(entity = Login.class,
  parentColumns = "signup_username", childColumns = "login_username",
  onDelete = ForeignKey.CASCADE))*/
@Entity(tableName = "accounts",
        indices = {@Index(value = "username", unique = true)})
public class Account {

    @PrimaryKey(autoGenerate = true)
    private int accountId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public Account(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int id) {
        this.accountId = id;
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

    @Override
    public String toString() {
        return "SignUp{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
