package com.example.assignmentand1x.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.assignmentand1x.viewModel.EditOfferViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.spark.submitbutton.SubmitButton;
import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class EditOfferActivity extends AppCompatActivity {

    ImageView imageView;
    SubmitButton editButton;
    Button imageButton;
    EditText email;
    EditText title;
    EditText time;
    EditText date;
    EditText localization;
    EditText description;
    EditOfferViewModel viewModel;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_offer);
        viewModel = new ViewModelProvider(this).get(EditOfferViewModel.class);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // FIND VIEWS
        email = findViewById(R.id.editEmailEditText);
        title = findViewById(R.id.editeditTextTitle);
        time = findViewById(R.id.editeditTextTime);
        date = findViewById(R.id.editeditTextDate);
        localization = findViewById(R.id.editLocalizationEditText);
        description = findViewById(R.id.editDescriptionEditText);
        imageView = (ImageView) findViewById(R.id.editPhotoView);
        editButton = findViewById(R.id.buttonEditOffer1);
        // FIND VIEWS - END

        // SET FIELDS
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

        // END OF SET FIELDS




        editButton.setOnClickListener(v -> {
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
                viewModel.getOffer(idReceived).observe(this, offer->{
                    offer.setDate(dateText);
                    offer.setDescription(descriptionText);
                    offer.setEmail(emailText);
                    offer.setTitle(titleText);
                    offer.setLocalization(localizationText);
                    offer.setPhoto(toByteArray(imageView.getDrawable()));
                    offer.setTime(timeText);

                    viewModel.update(offer);
                });

                System.out.println(UserContext.getLoggedUserId());
                Context context = getApplicationContext();
                Intent intent = new Intent(getApplicationContext(),OfferActivity.class);
                intent.putExtra("id",idReceived);
                intent.putExtra("offerAccountId",idAccountReceived);
                intent.putExtra("title",titleText);
                intent.putExtra("email",emailText);
                intent.putExtra("date",dateText);
                intent.putExtra("time",timeText);
                intent.putExtra("localization",localizationText);
                intent.putExtra("description",descriptionText);
                intent.putExtra("photo",toByteArray(imageView.getDrawable()));
                startActivity(intent);
            }
        });

        imageButton = findViewById(R.id.editPhotoButton);
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
            case R.id.action_myOffers:
                startActivity(new Intent(getApplicationContext(), MyOffersActivity.class));
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