package com.example.kino;

import com.google.gson.annotations.SerializedName;

public class Rating {
    @SerializedName("kp")
    private String kinopoisk;

    public Rating(String kinopoisk) {
        this.kinopoisk = kinopoisk;
    }

    public String getKinopoisk() {
        return kinopoisk;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "kinopoisk='" + kinopoisk + '\'' +
                '}';
    }
}
