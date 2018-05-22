package com.example.android.movieposters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;


public class MovieDetails extends AppCompatActivity {

    public static final String TAG = "MovieDetails";


    private MovieResult mMovie;
    TextView mTitle;
    TextView mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviedetail);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }
        else
        {
            mTitle = findViewById(R.id.title);
            mDescription = findViewById(R.id.overview);

            mTitle.setText(mMovie.getOriginal_title());
            mDescription.setText(mMovie.getOverview());

        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, "Movie Details Not Found", Toast.LENGTH_SHORT).show();
    }

}
