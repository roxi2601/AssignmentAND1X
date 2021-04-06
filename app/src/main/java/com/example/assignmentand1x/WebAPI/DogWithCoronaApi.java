package com.example.assignmentand1x.WebAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DogWithCoronaApi {

    @GET("api/v2/offer/{id}")
    Call<DogWithCoronaResponse> getOffer(@Path("id") int id);
}
