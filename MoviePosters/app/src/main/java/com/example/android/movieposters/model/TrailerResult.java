package com.example.android.movieposters.model;

public class TrailerResult{

    private String key;
    private String name;

    public TrailerResult(String key, String name)
    {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key){
        this.key = key;
    }

    public String getName() {
        return name;
    }
    public void setName()
    {
        this.name = name;
    }
}
