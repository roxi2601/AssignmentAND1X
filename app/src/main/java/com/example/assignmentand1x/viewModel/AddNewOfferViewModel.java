package com.example.assignmentand1x.viewModel;


import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.example.assignmentand1x.model.Offer;
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
}
