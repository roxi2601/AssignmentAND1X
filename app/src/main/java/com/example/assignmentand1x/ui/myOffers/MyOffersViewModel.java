package com.example.assignmentand1x.ui.myOffers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyOffersViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyOffersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is my offers fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}