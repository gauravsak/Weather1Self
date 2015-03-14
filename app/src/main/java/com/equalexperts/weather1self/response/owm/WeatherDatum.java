package com.equalexperts.weather1self.response.owm;

import com.equalexperts.weather1self.model.Event;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherDatum {
    public static final List<String> OBJECT_TAGS = Arrays.asList("weather", "city");
    public static final List<String> ACTION_TAGS = Arrays.asList("record");
    public static final Map<String, Object> PROPERTIES;
    private WeatherSummary main;
    private long dt;

    static {
        PROPERTIES = new HashMap<>();
    }

    private static DateTimeFormatter isoFormat;

    WeatherDatum() {
        isoFormat = ISODateTimeFormat.dateTime();
    }

    public BigDecimal getTemperature() {
        return main.getTemperature();
    }

    public String getISOTimestamp() {
        return isoFormat.print(new DateTime(dt * 1000));
    }

    public Event to1SelfEvent() {
        PROPERTIES.put("temperature", getTemperature());
        return new Event(OBJECT_TAGS, ACTION_TAGS, PROPERTIES,
                getISOTimestamp());
    }
}
