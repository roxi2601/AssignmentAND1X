package com.example.assignmentand1x.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.example.assignmentand1x.model.Offer;
import com.example.assignmentand1x.R;
import com.example.assignmentand1x.adapter.OfferAdapter;
import com.example.assignmentand1x.views.UserContext;
import com.example.assignmentand1x.viewModel.OfferViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainPageActivity extends AppCompatActivity {

    RecyclerView mOffersList;
    OfferAdapter mOffersAdapter;
    OfferViewModel offerViewModel;
    BottomNavigationView navigationMenu;
    FrameLayout frameLayout;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        frameLayout = findViewById(R.id.frameLayout);
        //NAVIGATION
        navigationMenu = findViewById(R.id.bottomNavViewId);
        navigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
        offerViewModel.getAllOffers().observe(this, new Observer<List<Offer>>() {
            @Override
            public void onChanged(List<Offer> offers) {
                mOffersAdapter.setOffers(offers);
            }
        });


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