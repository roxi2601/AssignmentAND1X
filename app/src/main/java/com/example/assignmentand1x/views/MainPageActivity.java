package com.example.assignmentand1x.views;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.assignmentand1x.R;
import com.example.assignmentand1x.adapter.OfferAdapter;
import com.example.assignmentand1x.model.Offer;
import com.example.assignmentand1x.viewModel.OfferViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.stream.Collectors;

public class MainPageActivity extends AppCompatActivity {

    RecyclerView mOffersList;
    OfferAdapter mOffersAdapter;
    OfferViewModel offerViewModel;
    BottomNavigationView navigationMenu;
    AppBarLayout frameLayout;
    ImageButton searchButton;
    EditText searchedLoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        offerViewModel = new ViewModelProvider(this).get(OfferViewModel.class);
        mOffersList = findViewById(R.id.rv);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mOffersList.setLayoutManager(mLayoutManager);
        mLayoutManager.setStackFromEnd(true);
        mLayoutManager.setReverseLayout(true);
        mOffersList.setHasFixedSize(true);
        mOffersAdapter = new OfferAdapter();
        mOffersList.setAdapter(mOffersAdapter);

        //get offers
        offerViewModel.getAllOffers().observe(this, offers -> mOffersAdapter.setOffers(offers));
        //---------------

        //find views
        searchedLoc = findViewById(R.id.searchView);
        searchButton = findViewById(R.id.searchButton);
        frameLayout = findViewById(R.id.frameLayout);
        navigationMenu = findViewById(R.id.bottomNavViewId2);
        //---------------


        //navigation
        navigationMenu.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    startActivity(new Intent(getApplicationContext(), MainPageActivity.class));
                    return true;
                case R.id.action_myOffers:
                    Intent intent = new Intent(getApplicationContext(), MyOffersActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.action_addoffer:
                    startActivity(new Intent(getApplicationContext(), AddNewOffer.class));
                    return true;
                case R.id.action_logout:
                    UserContext.logout();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    return true;
                default:
                    return false;
            }
        });
        //---------------

    }

    //search offer by localization
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getOffers(View view) {
        String searchedText = searchedLoc.getText().toString();
        if (!searchedText.isEmpty()) {
            offerViewModel.getAllOffers().observe(this, offers -> {
                List<Offer> filteredOffers = offers.stream()
                        .filter(offer -> offer.getLocalization().toLowerCase().contains(searchedText.toLowerCase()))
                        .collect(Collectors.toList());
                mOffersAdapter.setOffers(filteredOffers);
            });
        } else {
            offerViewModel.getAllOffers().observe(this, offers -> mOffersAdapter.setOffers(offers));
        }
    }
    //---------------

    //menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_home:
                startActivity(new Intent(this, MainPageActivity.class));
                return true;
            case R.id.action_addoffer:
                startActivity(new Intent(this, AddNewOffer.class));
                return true;
            case R.id.action_logout:
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    //---------------
}