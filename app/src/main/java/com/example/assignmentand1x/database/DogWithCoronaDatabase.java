package com.example.assignmentand1x.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.assignmentand1x.login.Login;
import com.example.assignmentand1x.login.LoginDao;
import com.example.assignmentand1x.login.LoginDatabase;
import com.example.assignmentand1x.offer.Offer;
import com.example.assignmentand1x.offer.OfferDao;
import com.example.assignmentand1x.signup.SignUp;
import com.example.assignmentand1x.signup.SignUpDao;

@Database(entities = {SignUp.class, Login.class, Offer.class},version = 1)
public abstract class DogWithCoronaDatabase extends RoomDatabase {

    private static DogWithCoronaDatabase instance;
    public abstract SignUpDao signUpDao();
    public abstract LoginDao loginDao();
    public abstract OfferDao offerDao();

    public static synchronized DogWithCoronaDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DogWithCoronaDatabase.class, "dogWithCorona_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
