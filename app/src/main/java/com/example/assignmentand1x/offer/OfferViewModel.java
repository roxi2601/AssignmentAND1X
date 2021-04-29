package com.example.assignmentand1x.offer;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.assignmentand1x.account.Account;

import java.util.List;

public class OfferViewModel extends AndroidViewModel {
    private final OfferRepository repository;
    private LiveData<List<Offer>> allOffers;
    public OfferViewModel(Application app){
        super(app);
        repository = OfferRepository.getInstance(app);
        allOffers = repository.getAllOffers();
    }
    public void addOffer (final Offer offer)
    {
        repository.insert(offer);
    }
    public void deleteOffer(final Offer offer)
    {
        repository.deleteOffer(offer);
    }
    public void updateOffer(final Offer offer)
    {
        repository.updateOffer(offer);
    }
    public Offer getOffer(int id){
        return repository.getOffer(id);
    }
    public LiveData<List<Offer>> getAllOffers(){ return allOffers;}
}
