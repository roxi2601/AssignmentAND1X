package com.example.assignmentand1x.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.assignmentand1x.model.Offer;
import com.example.assignmentand1x.repository.OfferRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class OfferViewModel extends AndroidViewModel {
    private final OfferRepository repository;
    private LiveData<List<Offer>> allOffers;
    public OfferViewModel(Application app){
        super(app);
        repository = OfferRepository.getInstance(app);
        allOffers = repository.getAllOffers();
    }

    public void deleteOffer(final int id)
    {
        repository.deleteOffer(id);
    }

    public LiveData<List<Offer>> getAllOffers(){ return allOffers;}
    public ArrayList<Offer> getOffers(String locSearch) throws ExecutionException, InterruptedException {
        return (ArrayList<Offer>) repository.getOffers(locSearch);
    }
}
