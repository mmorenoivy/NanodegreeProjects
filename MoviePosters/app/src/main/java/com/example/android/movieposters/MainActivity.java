package com.example.android.movieposters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * reference:
 * https://android--code.blogspot.com/2015/12/android-recyclerview-grid-layout-example.html
 * https://medium.com/@dds861/json-parsing-using-retrofit-and-recycleview-2300d9fdcf15
 * https://inthecheesefactory.com/blog/retrofit-2.0/en
 * https://antonioleiva.com/recyclerview/
 * https://www.androidhive.info/2016/05/android-working-with-retrofit-http-library/
 * https://medium.com/@dds861/json-parsing-using-retrofit-and-recycleview-2300d9fdcf15
 *
 * final code Stage 1 - 5/21/2018
 * coded with my 2 year old Mark
 * *
 */

public class MainActivity extends AppCompatActivity {


    private final static String BASE_URL = "https://api.themoviedb.org/3/";
    private final static String API_KEY = "f3221eeb46c806eb9a7fd5656581c50b";
    private final static int PAGE = 1;
    private final static String LANGUAGE = "en-US";
    private final static String CATEGORY = "popular";

    private RecyclerView mRecyclerView;
    private RecyclerView_Adapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycledMovies);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerViewAdapter = new RecyclerView_Adapter(this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new RecyclerView_Adapter.LoggingInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15,TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieAPI requestService = retrofit.create(MovieAPI.class);

        Call<MovieList> movieListCall = requestService.getMoviePopular();
        movieListCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                mRecyclerViewAdapter.setMovieList(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.menu_main, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }
}
