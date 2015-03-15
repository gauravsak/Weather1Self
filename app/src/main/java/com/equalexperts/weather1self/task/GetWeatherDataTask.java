package com.equalexperts.weather1self.task;

import android.os.AsyncTask;
import android.util.Log;

import com.equalexperts.weather1self.activity.DisplayWeatherActivity;
import com.equalexperts.weather1self.response.lib1Self.Stream;
import com.equalexperts.weather1self.response.owm.WeatherResponse;
import com.equalexperts.weather1self.service.OpenWeatherMapClient;

import org.joda.time.DateTime;

import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GetWeatherDataTask extends AsyncTask<Object, Void, WeatherResponse> {

    OpenWeatherMapClient weatherClient;
    Stream streamFor1Self;
    WeatherResponse weatherResponse;
    DateTime instant;
    String city;
    String country;

    public GetWeatherDataTask(Stream streamFor1Self, DateTime instant, String city, String country) {
        this.weatherClient = DisplayWeatherActivity.getOWMWeatherClient();
        this.streamFor1Self = streamFor1Self;
        this.instant = instant;
        this.city = city;
        this.country = country;
    }

    @Override
    protected WeatherResponse doInBackground(Object... params) {
        long today = instant.getMillis() / 1000;
        long eightDaysBeforeInstant = instant.minusDays(8).getMillis() / 1000;
        weatherClient.weatherFor(getCityAndCountryParam(city, country), new Callback<WeatherResponse>() {

            @Override
            public void success(WeatherResponse returnedWeatherResponse, Response response) {
                weatherResponse = returnedWeatherResponse;
                Log.d("getWeatherData", "weather response" + weatherResponse);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
        return weatherResponse;
    }

    @Override
    protected void onPostExecute(WeatherResponse weatherResponse) {
        super.onPostExecute(weatherResponse);
        new SendTemperatureEventsTo1SelfTask(weatherResponse, streamFor1Self).execute();
    }

    private static String getCityAndCountryParam(String city, String country) {
        return city + ",%20" + country.toLowerCase(Locale.ENGLISH);
    }
}