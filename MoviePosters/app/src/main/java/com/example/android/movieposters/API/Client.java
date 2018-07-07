package com.example.android.movieposters.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
   // public static Retrofit retrofit = null;

    public static Retrofit getClient(){
     //   if (retrofit == null){
           Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
       // }
        return retrofit;
    }
}
