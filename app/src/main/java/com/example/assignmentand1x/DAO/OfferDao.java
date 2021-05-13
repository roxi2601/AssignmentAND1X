package com.example.assignmentand1x.DAO;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
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

    @Delete
    void deleteOffer(Offer offer);

    @Query("DELETE FROM offers WHERE id=:id")
    void deleteOffer(int id);

    @Query("SELECT * FROM offers WHERE id = :id")
    LiveData<Offer> getOffer(int id);

    @Query("SELECT * FROM offers ")
    LiveData<List<Offer>> getAllOffers();

    @Query("SELECT * FROM offers WHERE localization LIKE :locSearch")
    LiveData<List<Offer>> getOffers(String locSearch);


}
