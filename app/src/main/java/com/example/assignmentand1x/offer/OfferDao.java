package com.example.assignmentand1x.offer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignmentand1x.account.Account;

import java.util.List;

@Dao
public interface OfferDao {
    @Insert
    void insert(Offer offer);

    @Update
    void update(Offer offer);

    @Delete
    void deleteOffer(Offer offer);

    @Query("SELECT * FROM offers WHERE id = :id")
    Offer getOffer(int id);

    @Query("SELECT * FROM offers ")
    LiveData<List<Offer>> getAllOffers();
}
