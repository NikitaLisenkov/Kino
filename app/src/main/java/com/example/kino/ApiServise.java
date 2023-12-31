package com.example.kino;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServise {
    @GET("movie?token=ZJKVHXQ-AERMNTP-PBRP71B-VZE6TSH&field=rating.kp&search=7-10&sortField=votes.kp&sortType=-1&limit=30")
    Single<MovieResponse> loadMovies(
            @Query("page") int page);

    @GET("movie/{ID}?token=ZJKVHXQ-AERMNTP-PBRP71B-VZE6TSH")
    Single<TrailerResponce> loadTrailers(@Path("ID") int id);

    @GET("review?token=ZJKVHXQ-AERMNTP-PBRP71B-VZE6TSH")
    Single<ReviewResponse> loadReviews(
            @Query("movieId") int id);
}
