package com.example.android.movieposters.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.movieposters.MovieResult;
import com.example.android.movieposters.R;
import com.example.android.movieposters.RecyclerView_Adapter;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

//https://www.coderefer.com/android-parcelable-example/

public class MovieDetails extends AppCompatActivity {

    public static final String TAG = "MovieDetails";

    public String MOVIE = "movies";

    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w342";

    private MovieResult mMovie;
    private RecyclerView_Adapter recyclerView_adapter;
    ImageView imageView;
    TextView mTitle;
    TextView mOverview;
    TextView mReleaseDate;
    TextView mUserRating;
    RatingBar ratingBar;

    private final AppCompatActivity activity = MovieDetails.this;

    String thumbnail, movieName, movieDescription, userRating, releaseDate;
    int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        mTitle = (TextView) findViewById(R.id.title);
        imageView = (ImageView) findViewById(R.id.poster);
        mOverview = (TextView) findViewById(R.id.overview);

        //Convert the rating into a rating bar
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        mUserRating = (TextView) findViewById(R.id.rating);

        mReleaseDate = (TextView) findViewById(R.id.release_date);

        Intent intentStartDetails = getIntent();
        if(intentStartDetails.hasExtra(MOVIE)){

            mMovie = getIntent().getParcelableExtra(MOVIE);

            thumbnail = mMovie.getPoster_path();
            movieName = mMovie.getOriginal_title();
            movieDescription = mMovie.getOverview();
            releaseDate = mMovie.getRelease_date();
            //convert the float into readable decimal format of two digits
            double f = mMovie.getVote_average();

            DecimalFormat decimal = new DecimalFormat("#.##");
            float twoDigit = Float.valueOf(decimal.format(f));
            userRating = Double.toString(mMovie.getVote_average());

            //convert the user rating to a rating bar
            ratingBar.setRating(twoDigit);

            Picasso.with(this)
                    .load(TMDB_IMAGE_PATH + mMovie.getPoster_path())
                    .placeholder(R.color.colorPrimaryDark)
                    .into(imageView);

            mTitle.setText(movieName);
            mOverview.setText(movieDescription);
            mUserRating.setText(userRating);
            mReleaseDate.setText(releaseDate);
        }
        else
        {
            Toast.makeText(this, "No Movie Details Available", Toast.LENGTH_SHORT).show();
        }
     }
}
