package com.equalexperts.weather1self.response.wu;

import java.util.List;

public class WeatherResponse {

    private WeatherHistory history;

    public List<WeatherDatum> getWeatherData() {
        return history.getObservations();
    }
}
