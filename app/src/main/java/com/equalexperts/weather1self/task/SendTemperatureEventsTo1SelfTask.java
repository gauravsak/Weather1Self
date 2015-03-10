package com.equalexperts.weather1self.task;

import android.os.AsyncTask;
import android.util.Log;

import com.equalexperts.weather1self.activity.DisplayWeatherActivity;
import com.equalexperts.weather1self.response.Stream;
import com.equalexperts.weather1self.response.WeatherDatum;
import com.equalexperts.weather1self.response.WeatherResponse;
import com.equalexperts.weather1self.service.Lib1SelfClient;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SendTemperatureEventsTo1SelfTask extends AsyncTask<Object, Void, Void> {

    Lib1SelfClient lib1SelfClient;
    WeatherResponse weatherResponse;
    Stream streamFor1Self;

    public SendTemperatureEventsTo1SelfTask(WeatherResponse weatherResponse, Stream streamFor1Self) {
        lib1SelfClient = DisplayWeatherActivity.get1SelfClient();
        this.weatherResponse = weatherResponse;
        this.streamFor1Self = streamFor1Self;
    }

    @Override
    protected Void doInBackground(Object... params) {

        for (final WeatherDatum weatherDatum : weatherResponse.getWeatherData()) {
            lib1SelfClient.sendEvent(weatherDatum.to1SelfEvent(), streamFor1Self.getId(),
                    streamFor1Self.getWriteToken(), new Callback<Void>() {

                        @Override
                        public void success(Void aVoid, Response response) {
                            // Nothing to do
                            Log.d("sendEventsTo1Self", "sending temperature events to 1Self");
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            error.printStackTrace();
                        }
                    });
        }
        return null;
    }
}
