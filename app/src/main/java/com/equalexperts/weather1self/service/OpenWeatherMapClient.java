package com.equalexperts.weather1self.service;

import com.equalexperts.weather1self.response.WeatherResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface OpenWeatherMapClient {

    String API_URL = "http://api.openweathermap.org/data/2.5";

    @GET("/history/city")
    void weatherFor(
            @Query("q") String cityAndCountry,
            @Query("start") Long fromInstant,
            @Query("end") Long toInstant,
            Callback<WeatherResponse> callback
    );

    @GET("/history/city")
    void weatherFor(
            @Query("q") String cityAndCountry,
            Callback<WeatherResponse> callback
    );

    @GET("/history/city")
    WeatherResponse weatherFor(
            @Query("q") String cityAndCountry,
            @Query("start") Long fromInstant,
            @Query("end") Long toInstant
    );

    @GET("/history/city")
    WeatherResponse weatherFor(
            @Query("q") String cityAndCountry
    );

    @GET("/history/city")
    WeatherResponse weatherFor(
            @Query("q") String cityAndCountry,
            @Query("start") Long fromInstant,
            @Query("end") Long toInstant,
            @Query("type") String frequency
    );
}