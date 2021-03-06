package com.example.assignmentand1x.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.assignmentand1x.model.Offer;
import com.example.assignmentand1x.database.DogWithCoronaDatabase;
import com.example.assignmentand1x.DAO.OfferDao;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OfferRepository {
    OfferDao offerDao;

    ExecutorService executorService;
    private static OfferRepository instance;
    private LiveData<List<Offer>> allOffers;

    private OfferRepository(Application application) {

        DogWithCoronaDatabase database = DogWithCoronaDatabase.getInstance(application);
        offerDao = database.offerDao();
        allOffers = database.offerDao().getAllOffers();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized OfferRepository getInstance(Application application) {
        if (instance == null) {
            instance = new OfferRepository(application);
        }
        return instance;
    }

    public LiveData<Offer> getOffer(int id) {
        return offerDao.getOffer(id);
    }

    public LiveData<List<Offer>> getAllOffers() {
        return allOffers;
    }

    public void insert(Offer offer) {

        new InsertOfferAsyncTask(offerDao).execute(offer);
    }

    public void deleteOffer(int id) {
        executorService.execute(() -> offerDao.deleteOffer(id));

    }


    public void updateOffer(Offer offer) {
        executorService.execute(() -> offerDao.update(offer));
    }

    private static class InsertOfferAsyncTask extends AsyncTask<Offer, Void, Void> {
        private OfferDao offerDao;

        private InsertOfferAsyncTask(OfferDao offerDao) {
            this.offerDao = offerDao;
        }

        @Override
        protected Void doInBackground(Offer... offers) {
            offerDao.insert(offers[0]);
            return null;
        }
    }

}
