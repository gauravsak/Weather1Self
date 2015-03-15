package com.equalexperts.weather1self.response.wu;

public class DateTime {
    private String year;
    private String mon;
    private String mday;
    private String hour;
    private String min;

    public String toISOString() {
        String month = mon;
        String dayOfMonth = mday;
        String minute = min;
        return year + "-" + month + "-" + dayOfMonth + "T" + hour + ":" + minute + ":00.000Z";    // “2014-11-11T22:30:00.000Z”
    }
}
