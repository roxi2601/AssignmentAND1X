package com.example.assignmentand1x.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Blob;
import java.util.Arrays;

@Entity(tableName = "offers")
public class Offer {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String email;
    private String title;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] photo;
    private String time;
    private String date;
    private String localization;
    private String description;
    private int offerAccountId;

    public Offer(String email, String title, byte[] photo, String time, String date, String localization, String description, int offerAccountId) {
        this.email = email;
        this.title = title;
        this.photo = photo;
        this.time = time;
        this.date = date;
        this.localization = localization;
        this.description = description;
        this.offerAccountId = offerAccountId;
    }

    public boolean isOwnedBy(int accountId) {
        return accountId == this.offerAccountId;
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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


    public int getOfferAccountId() {
        return offerAccountId;
    }

    public void setOfferAccountId(int offerAccountId) {
        this.offerAccountId = offerAccountId;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", photo=" + Arrays.toString(photo) +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", localization='" + localization + '\'' +
                ", description='" + description + '\'' +
                ", offerAccountId=" + offerAccountId +
                '}';
    }
}
