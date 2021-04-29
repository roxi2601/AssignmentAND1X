package com.example.assignmentand1x.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.assignmentand1x.account.Account;
import com.example.assignmentand1x.account.AccountDao;
import com.example.assignmentand1x.account.AccountRepository;
import com.example.assignmentand1x.offer.Offer;
import com.example.assignmentand1x.offer.OfferDao;

@Database(entities = {Account.class, Offer.class}, version = 5)
public abstract class DogWithCoronaDatabase extends RoomDatabase {
    private static DogWithCoronaDatabase instance;
    public abstract AccountDao accountDao();
    public abstract OfferDao offerDao();


    public static synchronized DogWithCoronaDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DogWithCoronaDatabase.class, "dwc_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}

