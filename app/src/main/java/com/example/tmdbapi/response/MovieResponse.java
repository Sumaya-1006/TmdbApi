package com.example.tmdbapi.response;

import com.example.tmdbapi.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {
    // single movie response

    @SerializedName("results")
    @Expose
    private MovieModel movie;

    public MovieResponse() {
    }

    public MovieModel getMovie() {
        return movie;
    }

    public void setMovie(MovieModel movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
