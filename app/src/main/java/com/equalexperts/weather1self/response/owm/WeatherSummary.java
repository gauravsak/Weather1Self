package com.equalexperts.weather1self.response.owm;

import java.math.BigDecimal;

public class WeatherSummary {
    private BigDecimal temp;

    public static final BigDecimal ONE_DEGREE_KELVIN = BigDecimal.valueOf(273.16);

    public BigDecimal getTemperature() {
        return temp.subtract(ONE_DEGREE_KELVIN).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
