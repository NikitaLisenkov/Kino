package com.example.kino;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TrailerResponce {
    @SerializedName("videos")
    private TrailersList trailersList;

    public TrailerResponce(TrailersList trailersList) {
        this.trailersList = trailersList;
    }

    public TrailersList getTrailersList() {
        return trailersList;
    }

    @Override
    public String toString() {
        return "TrailerResponce{" +
                "trailersList=" + trailersList +
                '}';
    }
}
