package com.example.assignmentand1x.app;

import com.squareup.picasso.Picasso;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Picasso.setSingletonInstance(new Picasso.Builder(this).build());
    }
}
