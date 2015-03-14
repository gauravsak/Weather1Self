package com.equalexperts.weather1self.response.wu;

import java.util.List;

public class WeatherResponse {

    private List<WeatherDatum> observations;

    public List<WeatherDatum> getWeatherData() {
        return observations;
    }
}
