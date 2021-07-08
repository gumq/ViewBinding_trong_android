package com.tranlequyen.javasamplemvvm.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tranlequyen.javasamplemvvm.BuildConfig;
import com.tranlequyen.javasamplemvvm.model.MovieList;
import com.tranlequyen.javasamplemvvm.model.MovieResponse;
import com.tranlequyen.javasamplemvvm.remoteservice.ApiClient;
import com.tranlequyen.javasamplemvvm.remoteservice.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieViewModel extends ViewModel {
    private MutableLiveData<ArrayList<MovieList>> listMovie;

    public LiveData<ArrayList<MovieList>> getMovies() {
        if (listMovie == null) {
            listMovie = new MutableLiveData<>();
            loadMovies();
        }
        return listMovie;
    }

    private void loadMovies() { //do an asynchronous operation to fetch movies
        ApiInterface remoteService = ApiClient.getApiRemoteService();
        Call<MovieResponse> movieResponseCall = remoteService.getMovieList(BuildConfig.API_KEY, "vi");
        movieResponseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        ArrayList<MovieList> movieList = response.body().getResults();
                        listMovie.postValue(movieList);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                Log.e("INI", "ERROR -> " + t);
            }
        });
    }
}
