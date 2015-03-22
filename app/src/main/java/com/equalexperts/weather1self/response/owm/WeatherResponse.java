package com.equalexperts.weather1self.response.owm;

import java.util.List;

public class WeatherResponse {
    private String city_id;
    private List<WeatherDatum> list;

    public List<WeatherDatum> getWeatherData() {
        return list;
    }

    @Override
    public String toString() {
        return "[" + city_id + ", " + list.size() + " records]";
    }
}
