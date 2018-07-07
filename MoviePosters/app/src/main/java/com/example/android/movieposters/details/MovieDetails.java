package com.example.android.movieposters.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.movieposters.API.Client;
import com.example.android.movieposters.API.MovieAPI;
import com.example.android.movieposters.R;
import com.example.android.movieposters.adapter.Movie_Adapter;
import com.example.android.movieposters.adapter.TrailerAdapter;
import com.example.android.movieposters.model.MovieResult;
import com.example.android.movieposters.model.Trailer;
import com.example.android.movieposters.model.TrailerResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//https://www.coderefer.com/android-parcelable-example/

public class MovieDetails extends AppCompatActivity {

    public static final String TAG = "MovieDetails";

    public String MOVIE = "movies";

    public List<TrailerResult> trailerResultList;

    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w342";
    public static final String TMDB_BACKDROP_PATH = "http://image.tmdb.org/t/p/original";
    private static final String YOUTUBE_URL = "https://www.youtube.com/watch?v=";
    public final static String API_KEY = "f3221eeb46c806eb9a7fd5656581c50b";

    private MovieResult mMovie;
    private RecyclerView recyclerView;
    private Movie_Adapter movie_adapter;
    private RecyclerView trailer_recyclerview;
    private TrailerAdapter trailer_adapter;

    public List<TrailerResult> trailersList;

    ImageView poster, hero;
    TextView mTitle, mOverview, mReleaseDate, mUserRating, mTextRating;
    private ProgressBar progressBar;
    RatingBar ratingBar;

    private final AppCompatActivity activity = MovieDetails.this;

    MovieResult movie;
    String hero_poster, thumbnail, movieName, movieDescription, userRating, releaseDate;
    int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        mTitle = (TextView) findViewById(R.id.title);
        poster = (ImageView) findViewById(R.id.hero_poster);
        hero = (ImageView) findViewById(R.id.poster);
        mOverview = (TextView) findViewById(R.id.overview);
        progressBar = findViewById(R.id.progress_bar);

        //Convert the rating into a rating bar
        ratingBar = (RatingBar) findViewById(R.id.rating_Bar);
        mTextRating = (TextView) findViewById(R.id.tv_rating);
        mUserRating = (TextView) findViewById(R.id.rating);
        mReleaseDate = (TextView) findViewById(R.id.release_date);

        Intent intentStartDetails = getIntent();
        if(intentStartDetails.hasExtra(MOVIE)){

            mMovie = getIntent().getParcelableExtra(MOVIE);

            hero_poster = mMovie.getBackdrop_path();
            thumbnail = mMovie.getPoster_path();

            movieName = mMovie.getOriginal_title();
            movieDescription = mMovie.getOverview();
            userRating = mMovie.getVote_average();
            releaseDate = mMovie.getRelease_date();
            movieId = movie.getId();

            Picasso.with(this)
                    .load(TMDB_BACKDROP_PATH + mMovie.getPoster_path())
                    .placeholder(R.color.colorPrimaryDark)
                    .into(poster);

            Picasso.with(this)
                    .load(TMDB_IMAGE_PATH + mMovie.getBackdrop_path())
                    .placeholder(R.color.colorPrimaryDark)
                    .into(hero);

            mTitle.setText(movieName);
            mOverview.setText(movieDescription);
            ratingBar.setRating(Float.valueOf(userRating)/2);
            mTextRating.setText("Rating: ");
            mUserRating.setText(userRating);
            mReleaseDate.setText("Released Date: " + releaseDate);
        }
        else
        {
            Toast.makeText(this, "No Movie Details Available", Toast.LENGTH_SHORT).show();
        }

        initViews();
     }

    private void initViews(){
        trailer_recyclerview = findViewById(R.id.trailer_recycler_view);
        trailer_recyclerview.setLayoutManager(new LinearLayoutManager(this));


        trailersList = new ArrayList<>();
        trailer_adapter = new TrailerAdapter(this, trailersList);

        recyclerView = (RecyclerView) findViewById(R.id.recycledMovies);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(movie_adapter);
        movie_adapter.notifyDataSetChanged();

        loadJSON();

    }

    private void loadJSON()
    {
        try{
            Client Client = new Client();
            MovieAPI apiService = Client.getClient().create(MovieAPI.class);
            Call<Trailer> call = apiService.getMovieTrailers(movieId, API_KEY);
            call.enqueue(new Callback<Trailer>() {
                @Override
                public void onResponse(Call<Trailer> call, Response<Trailer> response) {
                    List<TrailerResult> trailer = response.body().getTrailerResultList();
                    recyclerView.setAdapter(new TrailerAdapter(getApplicationContext(), trailer));
                    recyclerView.smoothScrollToPosition(0);
                }

                @Override
                public void onFailure(Call<Trailer> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                  //  Toast.makeText(this, "Error fetching trailer data", Toast.LENGTH_SHORT).show();

                }
            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
