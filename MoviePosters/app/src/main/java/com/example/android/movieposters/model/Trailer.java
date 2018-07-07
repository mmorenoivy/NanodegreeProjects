package com.example.android.movieposters.model;

import java.util.List;

//create a list of the trailer videos from TrailerResults.class
public class Trailer {

   /* ArrayList<TrailerResult> trailerResults;

    public ArrayList<TrailerResult> getTrailerResults() {
        return trailerResults;
    }*/

   private int trailer_id;
   private List<TrailerResult> trailerResultList;

   public int getTrailer_id(){
       return trailer_id;
   }

   public void setTrailer_id(int trailer_id){
       this.trailer_id = trailer_id;
   }

   public List<TrailerResult> getTrailerResultList()
   {
       return trailerResultList;
   }
}
