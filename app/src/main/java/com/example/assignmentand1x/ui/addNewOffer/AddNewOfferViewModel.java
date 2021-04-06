package com.example.assignmentand1x.ui.addNewOffer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddNewOfferViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddNewOfferViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is add ne offer fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}