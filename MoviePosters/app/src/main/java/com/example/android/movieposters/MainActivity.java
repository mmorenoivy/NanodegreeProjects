package com.example.android.movieposters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * reference:
 * https://android--code.blogspot.com/2015/12/android-recyclerview-grid-layout-example.html
 * *
 * */

public class MainActivity extends AppCompatActivity {

    private final static String API_KEY = "f3221eeb46c806eb9a7fd5656581c50b";
    private final static String BASE_URL = "https://api.themoviedb.org";
    private final static int PAGE=1;
    private final static String LANGUAGE = "en-US";
    private final static String CATEGORY = "popular";

    private RecyclerView mRecyclerView;
    private RecyclerView_Adapter mRecyclerViewAdapter;

/*
    private Toolbar mTopToolbar;

    RecyclerView recyclerView;

    Context context;

    RecyclerView.Adapter recyclerView_Adapter;

    RecyclerView.LayoutManager recyclerViewLayoutManager;

    String[] numbers = {
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",

    }; */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     /*   mTopToolbar = (Toolbar) findViewById(R.id.menu_action);
        setSupportActionBar(mTopToolbar);

        context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.recycledMovies);

        //Change 2 to your choice because here 2 is the number of Grid layout Columns in each row.
        recyclerViewLayoutManager = new GridLayoutManager(context, 2);

        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerView_Adapter = new RecyclerView_Adapter(context,numbers);

        recyclerView.setAdapter(recyclerView_Adapter);
*/
        mRecyclerView = (RecyclerView) findViewById(R.id.recycledMovies);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerViewAdapter = new RecyclerView_Adapter(this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        List<MovieResult> movies = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            movies.add(new MovieResult());
        }
        mRecyclerViewAdapter.setMovieList(movies);

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
*/
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageView;
        public ViewHolder(View itemView)
        {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.menu_action) {
//            Toast.makeText(MainActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
