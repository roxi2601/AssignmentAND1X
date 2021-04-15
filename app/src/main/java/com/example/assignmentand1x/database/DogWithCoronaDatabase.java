package com.example.assignmentand1x.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.assignmentand1x.account.Account;
import com.example.assignmentand1x.account.AccountDao;
import com.example.assignmentand1x.account.AccountRepository;

@Database(entities = {Account.class}, version = 4)
public abstract class DogWithCoronaDatabase extends RoomDatabase {
    private static DogWithCoronaDatabase instance;
    public abstract AccountDao accountDao();


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

