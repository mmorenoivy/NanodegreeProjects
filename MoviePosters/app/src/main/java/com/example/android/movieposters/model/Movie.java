package com.example.android.movieposters.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Movie implements Parcelable{
  /*  private int total_pages;

    public int getTotal_pages() {
        return total_pages;
    }*/

    private int page;
    private List<MovieResult> results;
    private int total_results;
    private int total_pages;

    public Movie(){

    }


    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public List<MovieResult> getResults() {
        return results;
    }

    public List<MovieResult> getMovies(){
        return results;
    }

    public void setResults(List<MovieResult> results){
        this.results = results;
    }

    public void setMovies(List<MovieResult> results){
        this.results = results;
    }

    public int getTotalResults(){
        return total_results;
    }

    public void setTotalResults(int total_results)
    {
        this.total_results = total_results;
    }

    public int getTotalPages() {
        return total_pages;
    }

    public void setTotalPages(int total_pages) {
        this.total_pages = total_pages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(page);
        parcel.writeTypedList(results);
        parcel.writeInt(total_results);
        parcel.writeInt(total_pages);
    }


    protected Movie(Parcel in) {
        page = in.readInt();
        results = in.createTypedArrayList(MovieResult.CREATOR);
        total_results = in.readInt();
        total_pages = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
/*
    public ArrayList<MovieResult> getResults() {
        return results;
    }*/


}
