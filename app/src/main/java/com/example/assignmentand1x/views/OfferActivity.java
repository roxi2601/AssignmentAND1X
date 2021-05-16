package com.example.assignmentand1x.views;


import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignmentand1x.R;
import com.example.assignmentand1x.viewModel.OfferViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;


public class OfferActivity extends AppCompatActivity {
    TextView title;
    TextView email;
    TextView date;
    TextView time;
    TextView localization;
    TextView description;
    ImageView imageView;
    ImageButton imageButton;
    Button editButton;
    Button contactButton;
    BottomNavigationView navigationMenu;
    OfferViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        viewModel = new ViewModelProvider(this).get(OfferViewModel.class);

        //find views
        title = findViewById(R.id.titleOffer);
        email = findViewById(R.id.textViewContectMe2);
        date = findViewById(R.id.textViewDateOffer2);
        time = findViewById(R.id.textViewTimeOffer2);
        localization = findViewById(R.id.textViewLocalizationOffer2);
        description = findViewById(R.id.textViewDescriptionOffer);
        imageView = findViewById(R.id.photoOffer);
        imageButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);
        contactButton = findViewById(R.id.buttonContact);
        //---------------


        //contact by email
        contactButton.setOnClickListener(v ->
        {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType(ClipDescription.MIMETYPE_TEXT_PLAIN);
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject for email");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, "Description for email");
            startActivity(Intent.createChooser(intent, "Send Email"));
        });
        //---------------

        //set fields
        Bundle bundleId = getIntent().getExtras();
        int idReceived = bundleId.getInt("id");

        Bundle bundleAccountId = getIntent().getExtras();
        int idAccountReceived = bundleAccountId.getInt("offerAccountId");

        Bundle bundle = getIntent().getExtras();
        String titleReceived = bundle.getString("title");
        title.setText(titleReceived);

        Bundle bundle2 = getIntent().getExtras();
        String emailReceived = bundle2.getString("email");
        email.setText(emailReceived);

        Bundle bundle3 = getIntent().getExtras();
        String dateReceived = bundle3.getString("date");
        date.setText(dateReceived);

        Bundle bundle4 = getIntent().getExtras();
        String timeReceived = bundle4.getString("time");
        time.setText(timeReceived);

        Bundle bundle5 = getIntent().getExtras();
        String locReceived = bundle5.getString("localization");
        localization.setText(locReceived);

        Bundle bundle6 = getIntent().getExtras();
        String descriptionReceived = bundle6.getString("description");
        description.setText(descriptionReceived);

        Bundle bundle7 = getIntent().getExtras();
        byte[] byteArray = bundle7.getByteArray("photo");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageView.setImageBitmap(bmp);

        //---------------

        //set button visibility
        if (idAccountReceived != UserContext.getLoggedUserId()) {
            imageButton.setVisibility(View.INVISIBLE);
            editButton.setVisibility(View.INVISIBLE);
            contactButton.setVisibility(View.VISIBLE);
        } else {
            imageButton.setVisibility(View.VISIBLE);
            editButton.setVisibility(View.VISIBLE);
            contactButton.setVisibility(View.INVISIBLE);

        }
        //---------------


        //delete
        imageButton.setOnClickListener(v -> {
            viewModel.deleteOffer(idReceived);
            startActivity(new Intent(this, MainPageActivity.class));
        });
        //---------------


        //update
        editButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), EditOfferActivity.class);
            intent.putExtra("id", idReceived);
            intent.putExtra("offerAccountId", idAccountReceived);
            intent.putExtra("title", titleReceived);
            intent.putExtra("email", emailReceived);
            intent.putExtra("date", dateReceived);
            intent.putExtra("time", timeReceived);
            intent.putExtra("localization", locReceived);
            intent.putExtra("description", descriptionReceived);
            intent.putExtra("photo", byteArray);
            startActivity(intent);
        });
        //---------------

        //navigation bar
        navigationMenu = findViewById(R.id.bottomNavViewId2);
        navigationMenu.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    startActivity(new Intent(getApplicationContext(), MainPageActivity.class));
                    return true;
                case R.id.action_myOffers:
                    startActivity(new Intent(getApplicationContext(), MyOffersActivity.class));
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

        // BACK (MENU)
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //---------------

    }

}