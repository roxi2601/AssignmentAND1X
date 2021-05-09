package com.example.assignmentand1x.viewModel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.assignmentand1x.model.Account;
import com.example.assignmentand1x.model.Offer;
import com.example.assignmentand1x.repository.AccountRepository;
import com.example.assignmentand1x.repository.OfferRepository;

public class AddNewOfferViewModel extends AndroidViewModel {
    private final OfferRepository repository;
    public AddNewOfferViewModel(Application app){
        super(app);
        repository = OfferRepository.getInstance(app);
    }
    public void insert(Offer offer){
        repository.insert(offer);
    }
    public LiveData<Offer> getOffer(int id){
        return repository.getOffer(id);
    }
}
