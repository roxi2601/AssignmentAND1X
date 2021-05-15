package com.example.assignmentand1x.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.assignmentand1x.R;
import com.example.assignmentand1x.model.Offer;
import com.example.assignmentand1x.viewModel.AddNewOfferViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.spark.submitbutton.SubmitButton;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

import com.example.assignmentand1x.webAPI.DogApiServices;

public class AddNewOffer extends AppCompatActivity {

    private DogApiServices dogApiServices;
    ImageView imageView;
    SubmitButton addButton;
    Button imageButton;
    EditText email;
    EditText title;
    EditText time;
    EditText date;
    EditText localization;
    EditText description;
    AddNewOfferViewModel viewModel;
    Button randomImage;
    ProgressBar progressBar;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_offer);
        viewModel = new ViewModelProvider(this).get(AddNewOfferViewModel.class);


        progressBar = findViewById(R.id.progressBar2);
        email = findViewById(R.id.emailEditText);
        title = findViewById(R.id.editTextTitle);
        time = findViewById(R.id.editTextTime);
        date = findViewById(R.id.editTextDate);
        localization = findViewById(R.id.localizationEditText);
        description = findViewById(R.id.descriptionEditText);
        imageView = findViewById(R.id.addPhotoView2);

        randomImage = findViewById(R.id.randomButton);

        randomImage.setOnClickListener(v->{
            if(hasInternetConnection()){
                dogApiServices = new DogApiServices(this);
                dogApiServices.getRandomImage(new DogApiServices.RandomResultCallBack() {
                    /**
                     * @param message
                     */
                    @Override
                    public void onRandomImageReceived(String message) {
                        Picasso.get().load(message).into(imageView);
                        String[] separated = message.split("/");
                        title.setText(separated[4].trim());
                    }

                    /**
                     * @param error
                     */
                    @Override
                    public void onRandomImageError(String error) {
                        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), getText(R.string.no_internet_connection), Toast.LENGTH_LONG).show();
            }
        });

        addButton = findViewById(R.id.buttonAddOffer);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        addButton.setOnClickListener(v -> {
            String emailText = email.getText().toString();
            String titleText = title.getText().toString();
            String timeText = time.getText().toString();
            String dateText = date.getText().toString();
            String localizationText = localization.getText().toString();
            String descriptionText = description.getText().toString();

            if (emailText.equals("") || titleText.equals("") || timeText.equals("") || dateText.equals("")
                    || localizationText.equals("") || descriptionText.equals("")) {
                Snackbar.make(v, "Fields cannot be empty", Snackbar.LENGTH_SHORT).show();
            } else {
                viewModel.insert(
                        new Offer(emailText,
                                titleText,
                                toByteArray(imageView.getDrawable()),
                                timeText,
                                dateText,
                                localizationText,
                                descriptionText,
                                UserContext.getLoggedUserId())
                );

                System.out.println(UserContext.getLoggedUserId());
                Context context = getApplicationContext();
                progressBar.setVisibility(View.VISIBLE);
                startActivity(new Intent(context, MainPageActivity.class));
            }

        });

        imageButton = findViewById(R.id.addPhotoButton);
        imageButton.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) // from https://www.youtube.com/watch?v=O6dWwoULFI8
            {
                Context context = getApplicationContext();
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED) {
                    //permission not granted, request it.
                    String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                    //show popup for runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);

                } else {
                    //permission already granted
                    pickPhotoFromGallery();
                }
            } else {
                pickPhotoFromGallery();
            }
        });


    }

    public boolean hasInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }
        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }
        return false;
    }

    private byte[] toByteArray(Drawable drawable) {
        BitmapDrawable bitmapDrawable = ((BitmapDrawable) drawable);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    private void pickPhotoFromGallery() {
        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    //handle result of runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission was granted
                    pickPhotoFromGallery();
                } else {
                    //permission was denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            //set image to image view
            imageView.setImageURI(data.getData());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_home:
                startActivity(new Intent(this, MainPageActivity.class));
                return true;
            case R.id.action_logout:
                UserContext.logout();
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}