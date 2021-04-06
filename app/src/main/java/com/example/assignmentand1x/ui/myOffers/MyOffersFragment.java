package com.example.assignmentand1x.ui.myOffers;

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

public class MyOffersFragment extends Fragment {

    private MyOffersViewModel myOffersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myOffersViewModel =
                new ViewModelProvider(this).get(MyOffersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_myoffers, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        myOffersViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}