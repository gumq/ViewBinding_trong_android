package com.tranlequyen.javasamplemvvm.remoteservice;

import com.tranlequyen.javasamplemvvm.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface ApiInterface {
    @GET("discover/movie")
    Call<MovieResponse> getMovieList(@Query("api_key") String apiKey, @Query("language") String language);
}
