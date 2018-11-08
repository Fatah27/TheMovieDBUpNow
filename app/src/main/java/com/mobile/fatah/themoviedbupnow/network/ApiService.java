package com.mobile.fatah.themoviedbupnow.network;

import com.mobile.fatah.themoviedbupnow.model.ResponseMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/upcoming")
    Call<ResponseMovie> getUpComingMoviee(@Query("api_key") String apikey,
                                             @Query("language") String language);
    @GET("movie/now_playing")
    Call<ResponseMovie> getNowPlaying(@Query("api_key") String apikey,
                                      @Query("language") String language);
}
