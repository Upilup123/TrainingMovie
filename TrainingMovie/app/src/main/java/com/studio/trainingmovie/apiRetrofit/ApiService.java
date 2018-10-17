package com.studio.trainingmovie.apiRetrofit;

import com.studio.trainingmovie.model.ResponseMovie;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie/upcoming")
    Call<ResponseMovie> getUpComingMovie(@Query("api_key") String apikey,
                                         @Query("language") String language );

}
