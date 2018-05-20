package com.example.android.movieposters;

import retrofit2.Call;
import retrofit2.http.GET;

//TODO 4 Create an API to correspond with an endpoint of the REST API
//we reference the interface with the following API calls - @POST, @UPDATE, @PATCH, @DELETE, and @GET requests
// this is where the retrofit comes in

public interface MovieAPI {
    @GET("movie/popular")
    Call<MovieList> getMoviePopular();
}
