package com.example.android.movieposters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_Adapter extends RecyclerView.Adapter<MainActivity.ViewHolder> {
   /* String[] values;
    Context context1;
*/
    private List<MovieResult> mMovieResult;
    private LayoutInflater mInflater;
    private Context mContext;


    //This constructor is responsible for passing values to the list
    public RecyclerView_Adapter(Context context) { //, String[] values2
   /*     values = values2;
        context1 = context2;*/
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mMovieResult = new ArrayList<>();
    }

    @Override
    public MainActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.movie_image, parent, false);
        MainActivity.ViewHolder viewHolder = new MainActivity.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainActivity.ViewHolder holder, int position) {
        MovieResult movie = mMovieResult.get(position);

        // This is how we use Picasso to load images from the internet.
        Picasso.with(mContext)
                .load(movie.getPoster_path())
                .placeholder(R.color.colorAccent)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return (mMovieResult == null) ? 0 : mMovieResult.size();
    }

    public void setMovieList(List<MovieResult> movieList)
    {
        this.mMovieResult.clear();
        this.mMovieResult.addAll(movieList);
        // The adapter needs to know that the data has changed. If we don't call this, app will crash.
        notifyDataSetChanged();
    }

    //This is responsible for holding the views
    /*public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textViewName);
        }
    }*/
    /*@Override
    public RecyclerView_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout,parent,false);
        ViewHolder viewHolder1 = new ViewHolder(view1);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(RecyclerView_Adapter.ViewHolder holder, int position) {

        holder.textView.setText(values[position]);

        holder.textView.setBackgroundColor(Color.CYAN);

        holder.textView.setTextColor(Color.BLUE);
    }

    @Override
    public int getItemCount() {
        return values.length;
    }
    */


}
