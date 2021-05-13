package com.example.assignmentand1x.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.assignmentand1x.R;
import com.example.assignmentand1x.adapter.OfferAdapter;
import com.example.assignmentand1x.model.Offer;
import com.example.assignmentand1x.viewModel.MainActivityViewModel;
import com.example.assignmentand1x.viewModel.OfferViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainPageActivity extends AppCompatActivity {

    RecyclerView mOffersList;
    OfferAdapter mOffersAdapter;
    OfferViewModel offerViewModel;
    BottomNavigationView navigationMenu;
    AppBarLayout frameLayout;
    MainActivityViewModel viewModel;
    ImageButton searchButton;
    EditText searchedLoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.init();
        checkIfSignedIn();

        searchedLoc = findViewById(R.id.searchView);
        searchButton = findViewById(R.id.searchButton);

        frameLayout = findViewById(R.id.frameLayout);
        //NAVIGATION
        navigationMenu = findViewById(R.id.bottomNavViewId);
        navigationMenu.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    startActivity(new Intent(getApplicationContext(), MainPageActivity.class));
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



        offerViewModel = new ViewModelProvider(this).get(OfferViewModel.class);

        mOffersList = findViewById(R.id.rv);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mOffersList.setLayoutManager(mLayoutManager);
        mLayoutManager.setStackFromEnd(true);
        mLayoutManager.setReverseLayout(true);
        mOffersList.setHasFixedSize(true);
        mOffersAdapter = new OfferAdapter();
        mOffersList.setAdapter(mOffersAdapter);
        offerViewModel.getAllOffers().observe(this, offers -> mOffersAdapter.setOffers(offers));


    }
    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                String message = "Welcome " + user.getDisplayName();
            } else
                startLoginActivity();
        });
    }

    private void startLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void signOut(View v) {
        viewModel.signOut();
    }

    public void  getOffers(View view){
        if(!searchedLoc.getText().toString().isEmpty())
        {

            offerViewModel.getAllOffers().observe(this,offers -> {
                try {
                    offers = offerViewModel.getOffers(searchedLoc.getText().toString());
                    mOffersAdapter.setOffers(offers);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }

            });

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_home:
                startActivity(new Intent(this, MainPageActivity.class));
                return true;
            case R.id.action_addoffer:
                startActivity(new Intent(this, AddNewOffer.class));;
                return true;
            case R.id.action_logout:

                startActivity(new Intent(this, LoginActivity.class));;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}