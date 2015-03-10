package com.equalexperts.weather1self.task;

import android.os.AsyncTask;
import android.util.Log;

import com.equalexperts.weather1self.activity.DisplayWeatherActivity;
import com.equalexperts.weather1self.response.Stream;
import com.equalexperts.weather1self.service.Lib1SelfClient;

import org.joda.time.DateTime;

import java.util.concurrent.ExecutionException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Create1SelfStreamTask extends AsyncTask<Object, Void, Stream> {

    private final String city;
    private final String country;
    Lib1SelfClient lib1SelfClient;
    Stream streamFor1Self;

    public Create1SelfStreamTask(String city, String country) {
        this.lib1SelfClient = DisplayWeatherActivity.get1SelfClient();
        this.city = city;
        this.country = country;
    }

    @Override
    protected Stream doInBackground(Object... params) {
        lib1SelfClient.getStream(new Callback<Stream>() {

            @Override
            public void success(Stream createdStream, Response response) {
                streamFor1Self = createdStream;
                Log.d("create1SelfStream", "created 1Self stream" + streamFor1Self);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
        return streamFor1Self;
    }

    @Override
    protected void onPostExecute(Stream stream) {
        super.onPostExecute(stream);
        new GetWeatherDataTask(stream, new DateTime(), city, country).execute();
    }
}
