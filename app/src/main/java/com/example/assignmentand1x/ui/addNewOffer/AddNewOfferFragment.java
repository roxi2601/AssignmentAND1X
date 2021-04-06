package com.example.assignmentand1x.ui.addNewOffer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignmentand1x.R;

public class AddNewOfferFragment extends Fragment {

    private AddNewOfferViewModel addNewOfferViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addNewOfferViewModel =
                new ViewModelProvider(this).get(AddNewOfferViewModel.class);
        View root = inflater.inflate(R.layout.fragment_addnewoffer, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        addNewOfferViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}