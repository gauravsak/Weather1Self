package com.equalexperts.weather1self.response.wu;

import com.equalexperts.weather1self.model.Event;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherDatum {
    public static final List<String> OBJECT_TAGS = Arrays.asList("weather", "city");
    public static final List<String> ACTION_TAGS = Arrays.asList("record");
    public static final Map<String, Object> PROPERTIES;
    private String tempm;
    private DateTime date;

    static {
        PROPERTIES = new HashMap<>();
    }

    public BigDecimal getTemperature() {
        return new BigDecimal(tempm);
    }

    public String getISOTimestamp() {
        return date.toISOString();
    }

    public Event to1SelfEvent() {
        PROPERTIES.put("temperature", getTemperature());
        return new Event(OBJECT_TAGS, ACTION_TAGS, PROPERTIES,
                getISOTimestamp());
    }
}
