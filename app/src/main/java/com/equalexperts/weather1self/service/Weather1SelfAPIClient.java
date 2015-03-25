package com.equalexperts.weather1self.service;

import com.equalexperts.weather1self.response.lib1Self.TemperatureChart;

import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Query;

public interface Weather1SelfAPIClient {

    String API_BASE_URL = "http://weather1self-api.heroku.com";

    @GET("/")
    public TemperatureChart getWeatherBarChartURI(@Query("city") String city, @Query("country") String country,
                                        @Header("x-weather-source") String weatherSource);
}
