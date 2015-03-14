package com.equalexperts.weather1self.response.owm;

import java.math.BigDecimal;

public class WeatherSummary {
    private BigDecimal temp;

    public BigDecimal getTemperature() {
        return temp.subtract(WeatherResponse.ONE_DEGREE_KELVIN).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
