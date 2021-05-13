package com.example.assignmentand1x.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.assignmentand1x.R;
import com.example.assignmentand1x.model.Offer;
import com.example.assignmentand1x.viewModel.AddNewOfferViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.spark.submitbutton.SubmitButton;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class AddNewOffer extends AppCompatActivity {

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
    private AppBarConfiguration mAppBarConfiguration;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_offer);
        viewModel = new ViewModelProvider(this).get(AddNewOfferViewModel.class);

        email = findViewById(R.id.emailEditText);
        title = findViewById(R.id.editTextTitle);
        time = findViewById(R.id.editTextTime);
        date = findViewById(R.id.editTextDate);
        localization = findViewById(R.id.localizationEditText);
        description = findViewById(R.id.descriptionEditText);
        imageView = (ImageView) findViewById(R.id.addPhotoView);

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
                startActivity(new Intent(context, MainPageActivity.class));
            }

        });

        imageButton = findViewById(R.id.addPhotoButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });


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