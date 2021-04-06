package com.example.assignmentand1x.WebAPI;
import com.example.assignmentand1x.WebAPI.DogWithCoronaApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class DogServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://dogwithcoronaapi.co")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static DogWithCoronaApi dogApi = retrofit.create(DogWithCoronaApi.class);

    public static DogWithCoronaApi getDogWithCoronaApi() {
        return dogApi;
    }
}
