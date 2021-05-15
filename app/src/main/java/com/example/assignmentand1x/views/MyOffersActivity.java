package com.example.assignmentand1x.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.assignmentand1x.R;
import com.example.assignmentand1x.adapter.OfferAdapter;
import com.example.assignmentand1x.model.Offer;
import com.example.assignmentand1x.viewModel.OfferViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;
import java.util.stream.Collectors;

public class MyOffersActivity extends AppCompatActivity {

    RecyclerView mOffersList;
    OfferAdapter mOffersAdapter;
    OfferViewModel offerViewModel;
    BottomNavigationView navigationMenu;
    AppBarLayout appBarLayout;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_offers);

        mOffersList = findViewById(R.id.rvMyOffers);
        appBarLayout = findViewById(R.id.myOffersLayout);
        offerViewModel = new ViewModelProvider(this).get(OfferViewModel.class);

        mOffersList = findViewById(R.id.rvMyOffers);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mOffersList.setLayoutManager(mLayoutManager);
        mLayoutManager.setStackFromEnd(true);
        mLayoutManager.setReverseLayout(true);
        mOffersList.setHasFixedSize(true);
        mOffersAdapter = new OfferAdapter();
        mOffersList.setAdapter(mOffersAdapter);
        offerViewModel.getAllOffers().observe(this,offers -> {

            List<Offer> filteredOffers =  offers.stream()
                    .filter(offer -> offer.getOfferAccountId()==UserContext.getLoggedUserId())
                    .collect(Collectors.toList());
            mOffersAdapter.setOffers(filteredOffers);

        });
        //NAVIGATION
        navigationMenu = findViewById(R.id.bottomNavViewId2);
        navigationMenu.getMenu().findItem(R.id.action_myOffers).setChecked(true);
        navigationMenu.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    Intent intent = new Intent(getApplicationContext(),MainPageActivity.class);
                    break;
                case R.id.action_myOffers:

                    startActivity(new Intent(getApplicationContext(), MyOffersActivity.class));
                    break;
                case R.id.action_addoffer:
                    startActivity(new Intent(getApplicationContext(), AddNewOffer.class));
                    break;
                case R.id.action_logout:
                    UserContext.logout();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    break;

            }
            return true;
        });


    }
}