package com.equalexperts.weather1self.response.owm;

import java.math.BigDecimal;
import java.util.List;

public class WeatherResponse {
    public static final BigDecimal ONE_DEGREE_KELVIN = BigDecimal.valueOf(273.16);
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
