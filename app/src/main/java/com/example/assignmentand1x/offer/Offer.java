package com.example.assignmentand1x.offer;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.example.assignmentand1x.account.Account;
@Entity(tableName = "offers")
public class Offer {
    @PrimaryKey(autoGenerate = true)
    private int id;
    String email;
    String title;
    int photoID;
    String  time;
    String date;
    String localization;
    String description;
    @Embedded
    Account ownerId;

    public Offer(String email, String title, int photoID, String time, String date, String localization, String description, Account ownerId) {
        this.email = email;
        this.title = title;
        this.photoID = photoID;
        this.time = time;
        this.date = date;
        this.localization = localization;
        this.description = description;
        this.ownerId = ownerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPhoto() {
        return photoID;
    }

    public void setPhoto(int photo) {
        this.photoID = photo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Account ownerId) {
        this.ownerId = ownerId;
    }
}
