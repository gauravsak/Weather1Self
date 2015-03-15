package com.equalexperts.weather1self.response.owm;

import com.equalexperts.weather1self.model.Event;
import com.equalexperts.weather1self.response.lib1Self.Eventful;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.equalexperts.weather1self.response.lib1Self.WeatherEventAttributes.ACTION_TAGS;
import static com.equalexperts.weather1self.response.lib1Self.WeatherEventAttributes.OBJECT_TAGS;
import static com.equalexperts.weather1self.response.lib1Self.WeatherEventAttributes.PROPERTY;

public class WeatherDatum implements Eventful {

    private WeatherSummary main;
    private long dt;

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

    @Override
    public Event to1SelfEvent() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(PROPERTY, getTemperature());
        return new Event(OBJECT_TAGS, ACTION_TAGS, properties, getISOTimestamp());
    }
}
