package com.equalexperts.weather1self.service;

import com.equalexperts.weather1self.response.wu.WeatherResponse;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

public interface WeatherUndergroundClient {

    String API_KEY = "d9be903ff6644e14";
    String API_URL = "http://api.wunderground.com/api/" + API_KEY;

    @Headers({
            "Accept-Encoding: gzip"
    })
    @GET("/history_{YYYYMMDD}/q/{country}/{city}.json")
    WeatherResponse weatherFor(
            @Path("city") String city,
            @Path("country") String country,
            @Path("YYYYMMDD") String date
    );
}
