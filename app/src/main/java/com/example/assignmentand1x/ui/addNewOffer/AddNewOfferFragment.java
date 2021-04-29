package com.example.assignmentand1x.ui.addNewOffer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignmentand1x.MainPageActivity;
import com.example.assignmentand1x.R;
import com.google.android.material.snackbar.Snackbar;

import static android.app.Activity.RESULT_OK;

public class AddNewOfferFragment extends Fragment {

    ImageView imageView;
    Button addButton;
    Button imageButton;
    Button exitButton; //to ominelam bo moze zrobie strzalke powrotna u gory
    private AddNewOfferViewModel addNewOfferViewModel;
    private  static  final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addNewOfferViewModel =
                new ViewModelProvider(this).get(AddNewOfferViewModel.class);
        View root = inflater.inflate(R.layout.fragment_addnewoffer, container, false);
        imageView = root.findViewById(R.id.addPhotoView);
        addButton = root.findViewById(R.id.buttonAddOffer);
        addButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MainPageActivity.class)); //???????
            }
        });
        imageButton = root.findViewById(R.id.addPhotoButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M) // from https://www.youtube.com/watch?v=O6dWwoULFI8
                {
                    if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED){
                        //permission not granted, request it.
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup for runtime permission
                        requestPermissions(permissions,PERMISSION_CODE);

                    }
                    else{
                        //permission already granted
                        pickPhotoFromGallery();
                    }
                }
                else {
                    pickPhotoFromGallery();
                }
            }
        });


        final TextView textView = root.findViewById(R.id.text_slideshow);
        addNewOfferViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                textView.setText(s);
            }

        });
        return root;
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
    switch (requestCode){
        case PERMISSION_CODE:{
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //permission was granted
                pickPhotoFromGallery();
            }
            else {
                //permission was denied
                Toast.makeText(getContext(),"Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == RESULT_OK && requestCode ==IMAGE_PICK_CODE)
        {
            //set image to image view
            imageView.setImageURI(data.getData());
        }
    }
}