package com.example.android.movieposters;

import android.graphics.Movie;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This is the model class which represents the movie details
 * It needs the following detail:
 * original title
 * movie poster image thumbnail
 * A plot synopsis (called overview in the api)
 * user rating (called vote_average in the api)
 * release date
 **/
public class MovieResult implements Parcelable {

    /**
     * vote_count : 3443
     * id : 299536
     * video : false
     * vote_average : 8.5
     * title : Avengers: Infinity War
     * popularity : 555.264536
     * poster_path : /7WsyChQLEftFiDOVTGkv3hFpyyt.jpg
     * original_language : en
     * original_title : Avengers: Infinity War
     * genre_ids : [12,878,14,28]
     * backdrop_path : /bOGkgRGdhrBYJSLpXaxhXVstddV.jpg
     * adult : false
     * overview : As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.
     * release_date : 2018-04-25
     */

    public String original_title;
    private String poster_path;
    private String overview;
    public float movieRating;

    public MovieResult()
    {

    }

    public MovieResult(String movieTitle, String moviePoster, String overview, float movieRating)
    {
        this.original_title = movieTitle;
        this.poster_path = moviePoster;
        this.overview = overview;
        this.movieRating = movieRating;
    }



/*
    private int vote_count;
    private double vote_average;
    private String poster_path;
    private String original_title;
    private String overview;
    private String release_date;

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster_path() {
        return poster_path;
        //return "http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg";
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

  @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.original_title);
        parcel.writeString(this.overview);
        parcel.writeString(this.poster_path);
        parcel.writeString(this.release_date);
        parcel.writeInt(this.vote_count);
        parcel.writeDouble(this.vote_average);
  }

    protected MovieResult(Parcel in) {
        //this.original_language = in.readString();
        this.original_title = in.readString();
        this.overview = in.readString();
        this.poster_path = in.readString();
       // this.generids = in.readString();
       // this.title = in.readString();
        this.release_date = in.readString();
       // this.backdrop_path = in.readString();

       // this.favourite = in.readInt();
       // this.id = in.readInt();
        this.vote_count = in.readInt();
        this.vote_average =in.readFloat();
       // this.popularity = in.readFloat();

    }
*/

    protected MovieResult(Parcel in) {
        original_title = in.readString();
        poster_path = in.readString();
        overview = in.readString();
        movieRating = in.readFloat();
    }

    public static final Creator<MovieResult> CREATOR = new Creator<MovieResult>() {
        @Override
        public MovieResult createFromParcel(Parcel in) {
            return new MovieResult(in);
        }

        @Override
        public MovieResult[] newArray(int size) {
            return new MovieResult[size];
        }
    };

    //write to parcelable

    public String getPoster_path() {
        return poster_path;
        //return "http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg";
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public float getMovieRating()
    {
        return movieRating;
    }

    public void setMovieRating(float movieRating)
    {
        this.movieRating = movieRating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(original_title);
        dest.writeString(poster_path);
        dest.writeFloat(movieRating);
    }



    /*//TODO 5 Create this class to reference the list in the MovieAPI interface
    public static class MovieList
    {
        private List<MovieResult> results;

        public List<MovieResult> getResults() {
            return results;
        }
    }
*/
}
