package com.example.tmdbapi.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tmdbapi.models.MovieModel;
import com.example.tmdbapi.response.MovieSearchResponse;
import com.example.tmdbapi.service.ApiInterface;
import com.example.tmdbapi.service.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {
    private static final String API_KEY = "7101f5fd5312cae06198b68a7737c325" ;
    private MutableLiveData<List<MovieModel>> moviesLiveData = new MutableLiveData<>();
    private ApiInterface tmdbService;

    public MovieViewModel() {
        tmdbService = RetrofitClient.getRetrofit().create(ApiInterface.class);
        fetchPopularMovies();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return moviesLiveData;
    }

    private void fetchPopularMovies() {
        Call<MovieSearchResponse> call = tmdbService.getPopularMovies(API_KEY,1);
        call.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    moviesLiveData.setValue(response.body().getMovies());
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                // Handle API call failure here.
                t.getStackTrace();
            }
        });
    }
}
