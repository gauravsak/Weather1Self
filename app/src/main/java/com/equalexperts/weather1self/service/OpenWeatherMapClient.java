package com.equalexperts.weather1self.service;

import com.equalexperts.weather1self.response.owm.WeatherResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

public interface OpenWeatherMapClient {

    String API_URL = "http://api.openweathermap.org/data/2.5";
    String API_KEY = "a19d564d7f6db6df2c4d18a3c218131d";

    @Headers({
            "x-api-key: " + API_KEY
    })
    @GET("/history/city")
    void weatherFor(
            @Query("q") String cityAndCountry,
            @Query("start") Long fromInstant,
            @Query("end") Long toInstant,
            Callback<WeatherResponse> callback
    );

    @Headers({
            "x-api-key: " + API_KEY
    })
    @GET("/history/city")
    void weatherFor(
            @Query("q") String cityAndCountry,
            Callback<WeatherResponse> callback
    );

    @Headers({
            "x-api-key: " + API_KEY
    })
    @GET("/history/city")
    WeatherResponse weatherFor(
            @Query("q") String cityAndCountry,
            @Query("start") Long fromInstant,
            @Query("end") Long toInstant
    );

    @Headers({
            "x-api-key: " + API_KEY
    })
    @GET("/history/city")
    WeatherResponse weatherFor(
            @Query("q") String cityAndCountry
    );

    @Headers({
            "x-api-key: " + API_KEY
    })
    @GET("/history/city")
    WeatherResponse weatherFor(
            @Query("q") String cityAndCountry,
            @Query("start") Long fromInstant,
            @Query("end") Long toInstant,
            @Query("type") String frequency
    );
}