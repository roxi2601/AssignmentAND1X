package com.example.assignmentand1x.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignmentand1x.model.Offer;

import java.util.List;

@Dao
public interface OfferDao {
    @Insert
    void insert(Offer offer);

    @Update
    void update(Offer offer);

    @Query("DELETE FROM offers WHERE id=:id")
    void deleteOffer(int id);

    @Query("SELECT * FROM offers WHERE id = :id")
    LiveData<Offer> getOffer(int id);

    @Query("SELECT * FROM offers ")
    LiveData<List<Offer>> getAllOffers();

}
