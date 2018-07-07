package com.example.android.movieposters.model;

import android.os.Parcel;
import android.os.Parcelable;

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
    private String vote_average;
    private String backdrop_path;
    private String poster_path;
    private String original_title;
    private String overview;
    private String release_date;
    private boolean isFavorite;
    private boolean isVideo;

    public MovieResult()
    {

    }

    public MovieResult(int id, String movieTitle, String movieHero,
                       String moviePoster, String overview,
                       String voteAverage, String releaseDate,
                       boolean isFave, boolean isVideo)
    {
        this.id = id;
        this.original_title = movieTitle;
        this.backdrop_path = movieHero;
        this.poster_path = moviePoster;
        this.overview = overview;
        this.vote_average = voteAverage;
        this.release_date = releaseDate;
        this.isFavorite = isFave;
        this.isVideo = isVideo;
    }

    //this part captures the data from the API
    protected MovieResult(Parcel in) {
        id = in.readInt();
        original_title = in.readString();
        backdrop_path = in.readString();
        poster_path = in.readString();
        overview = in.readString();
        vote_average = in.readString();
        release_date = in.readString();
        isFavorite = in.readByte() != 0; //add the favorites
        isVideo = (Boolean) in.readValue(Boolean.class.getClassLoader());
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
    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String voteAverage) {
        this.vote_average = voteAverage;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
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

    public boolean isFavorite(){ return isFavorite; }

    public void setFavorite(boolean isFav){
        isFavorite = isFav;
    }

    public Boolean getVideo() {
        return isVideo;
    }

    public void setVideo(Boolean video) {
        this.isVideo = video;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //this block takes the data from the protected MovieResult function, and writes it here
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(original_title);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeString(overview);
        dest.writeString(vote_average);
        dest.writeString(release_date);
        dest.writeByte((byte) (isFavorite ? 1 : 0)); //this evaluates if a movie is a favorite or not.
        dest.writeValue(isVideo);
    }

}
