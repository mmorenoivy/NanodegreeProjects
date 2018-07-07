package com.example.android.movieposters.details;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.movieposters.data.MovieResult;
import com.example.android.movieposters.R;
import com.example.android.movieposters.adapter.RecyclerView_Adapter;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

//https://www.coderefer.com/android-parcelable-example/

public class MovieDetails extends AppCompatActivity {

    public static final String TAG = "MovieDetails";

    public String MOVIE = "movies";

    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w342";
    public static final String TMDB_BACKDROP_PATH = "http://image.tmdb.org/t/p/original";


    private MovieResult mMovie;
    private RecyclerView_Adapter recyclerView_adapter;
    ImageView poster, hero;
    TextView mTitle;
    TextView mOverview;
    TextView mReleaseDate;
    TextView mUserRating;
    TextView mTextRating;

    private final AppCompatActivity activity = MovieDetails.this;

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

        //Convert the rating into a rating bar
    //    ratingBar = (RatingBar) findViewById(R.id.ratingBar);
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
            //convert the float into readable decimal format of two digits
            /*Double f = mMovie.getVote_average();

            DecimalFormat decimal = new DecimalFormat("#.##");
            float twoDigit = Float.valueOf(decimal.format(f));
            //userRating = Double.toString(mMovie.getVote_average());

            //convert the user rating to a rating bar
            ratingBar.setRating(twoDigit);*/

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
            mTextRating.setText("Rating: ");
            mUserRating.setText(userRating);
            mReleaseDate.setText(releaseDate);
        }
        else
        {
            Toast.makeText(this, "No Movie Details Available", Toast.LENGTH_SHORT).show();
        }
     }
}
