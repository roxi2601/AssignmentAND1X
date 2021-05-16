package com.example.assignmentand1x.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.assignmentand1x.model.Offer;
import com.example.assignmentand1x.repository.OfferRepository;

public class EditOfferViewModel extends AndroidViewModel {
    private final OfferRepository repository;

    public EditOfferViewModel(Application app) {
        super(app);
        repository = OfferRepository.getInstance(app);
    }

    public void update(Offer offer) {
        repository.updateOffer(offer);
    }

    public LiveData<Offer> getOffer(int id) {
        return repository.getOffer(id);
    }
}
