package com.example.kino;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServise {
    @GET("movie?token=ZJKVHXQ-AERMNTP-PBRP71B-VZE6TSH&field=rating.kp&search=7-10&sortField=votes.kp&soryType=-1&limit=40")
    Single<MovieResponse> loadMovies(
            @Query("page") int page);
}
