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

    private int id;
    private double vote_average;
    private String poster_path;
    private String original_title;
    private String overview;
    private String release_date;

    public MovieResult()
    {

    }

    public MovieResult(String movieTitle, String moviePoster, String overview, float movieRating, String release_date)
    {
        this.original_title = movieTitle;
        this.poster_path = moviePoster;
        this.overview = overview;
        this.vote_average = movieRating;
        this.release_date = release_date;
    }

    //this part captures the data from the API
    protected MovieResult(Parcel in) {
        original_title = in.readString();
        poster_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
        vote_average = in.readDouble();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster_path() {
        return poster_path;
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
    public int describeContents() {
        return 0;
    }

    //this block takes the data from the protected MovieResult function, and writes it here
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(original_title);
        dest.writeString(poster_path);
        dest.writeString(overview);
        dest.writeDouble(vote_average);
        dest.writeString(release_date);
    }
}
