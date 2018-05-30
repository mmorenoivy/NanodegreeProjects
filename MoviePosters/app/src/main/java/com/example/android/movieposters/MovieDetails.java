package com.example.android.movieposters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

//https://www.coderefer.com/android-parcelable-example/

public class MovieDetails extends AppCompatActivity {

    public static final String TAG = "MovieDetails";

    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    private MovieResult mMovie;
    private RecyclerView_Adapter recyclerView_adapter;
    ImageView imageView;
    TextView mTitle;
    TextView mDescription;
    TextView mUserRating;
    private final AppCompatActivity activity = MovieDetails.this;

    String thumbnail, movieName, movieDescription, userRating;
    int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviedetail);

        imageView = (ImageView) findViewById(R.id.poster);
        mTitle = (TextView) findViewById(R.id.title);
        mDescription = (TextView) findViewById(R.id.overview);
        mUserRating = (TextView) findViewById(R.id.rating);

//movies is a constant, TODO should declare movies as constant variable
        Intent intentStartDetails = getIntent();
        if(intentStartDetails.hasExtra("movies")){

            mMovie = getIntent().getParcelableExtra("movies");

            thumbnail = mMovie.getPoster_path();
            movieName = mMovie.getOriginal_title();
            movieDescription = mMovie.getOverview();
            userRating = Double.toString(mMovie.getMovieRating());
          //  movieId = mMovie.getId();

            Picasso.with(this)
                    .load(TMDB_IMAGE_PATH + mMovie.getPoster_path())
                    .placeholder(R.color.colorPrimaryDark)
                    .into(imageView);

            mTitle.setText(movieName);
            mDescription.setText(movieDescription);
            mUserRating.setText(userRating);
        }
        else
        {
            Toast.makeText(this, "No Movie Details Available", Toast.LENGTH_SHORT).show();
        }


   /*     Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }
        else
        {
            mTitle = findViewById(R.id.title);
         //   mDescription = findViewById(R.id.overview);

            mTitle.setText(mMovie.getOriginal_title());
         //   mDescription.setText(mMovie.getOverview());

        }*/
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, "Movie Details Not Found", Toast.LENGTH_SHORT).show();
    }

}
