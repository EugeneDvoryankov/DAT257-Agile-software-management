package com.example.eurythmics.response;

import com.example.eurythmics.models.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This class is for requesting movie lists like popular or latest movies
 */
public class MovieSearchResponse {

    @SerializedName("total_results") @Expose() private int total_count;

    @SerializedName("result") @Expose() private List<Movie> movies;

    public int getTotal_count(){
        return total_count;
    }

    public List<Movie> getMovies(){
        return movies;
    }


    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "total_count=" + total_count +
                ", movies=" + movies +
                '}';
    }
}
