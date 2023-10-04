package com.example.tmdbapi.service;

import com.example.tmdbapi.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/popular")
    Call<MovieSearchResponse> getPopularMovies(@Query("api_key") String apiKey,
                                             @Query("page") int page);
}
