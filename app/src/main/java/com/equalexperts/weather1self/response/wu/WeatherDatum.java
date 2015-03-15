package com.equalexperts.weather1self.response.wu;

import com.equalexperts.weather1self.model.Event;
import com.equalexperts.weather1self.response.lib1Self.Eventful;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.equalexperts.weather1self.response.lib1Self.WeatherEventAttributes.ACTION_TAGS;
import static com.equalexperts.weather1self.response.lib1Self.WeatherEventAttributes.OBJECT_TAGS;
import static com.equalexperts.weather1self.response.lib1Self.WeatherEventAttributes.PROPERTY;

public class WeatherDatum implements Eventful {
    private String tempm;
    private DateTime date;

    public BigDecimal getTemperature() {
        return new BigDecimal(tempm);
    }

    public String getISOTimestamp() {
        return date.toISOString();
    }

    @Override
    public Event to1SelfEvent() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(PROPERTY, getTemperature());
        return new Event(OBJECT_TAGS, ACTION_TAGS, properties, getISOTimestamp());
    }
}
