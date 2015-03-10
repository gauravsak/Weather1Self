package com.equalexperts.weather1self.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.equalexperts.weather1self.R;
import com.equalexperts.weather1self.model.Event;
import com.equalexperts.weather1self.response.Stream;
import com.equalexperts.weather1self.response.WeatherDatum;
import com.equalexperts.weather1self.response.WeatherResponse;
import com.equalexperts.weather1self.service.Lib1SelfClient;
import com.equalexperts.weather1self.service.OpenWeatherMapClient;
import com.equalexperts.weather1self.service.ServiceGenerator;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DisplayWeatherActivity extends ActionBarActivity {

    @InjectView(R.id.cityAndCountry)
    TextView cityAndCountryTextView;

    public static final String STREAM_ID = "com.equalexperts.weather1Self.STREAM_ID";
    public static final String READ_TOKEN = "com.equalexperts.weather1Self.READ_TOKEN";
    public static final String OBJECT_TAGS = "com.equalexperts.weather1Self.OBJECT_TAGS";
    public static final String ACTION_TAGS = "com.equalexperts.weather1Self.ACTION_TAGS";
    public static final String AGGREGATION= "com.equalexperts.weather1Self.AGGREGATION";
    public static final String PROPERTY = "com.equalexperts.weather1Self.PROPERTY";

    private Lib1SelfClient lib1SelfClient;
    private OpenWeatherMapClient weatherClient;
    private Stream streamFor1Self;
    private String city;
    private String country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_weather);
        ButterKnife.inject(this);
        Intent intent = getIntent();
        city = intent.getStringExtra(EnterCityDetailsActivity.CITY);
        country = intent.getStringExtra(EnterCityDetailsActivity.COUNTRY);
        String[] streamDetails = intent.getStringArrayExtra(EnterCityDetailsActivity.STREAM_DETAILS);
        streamFor1Self = new Stream(streamDetails[0], streamDetails[1], streamDetails[2]);

        new MainTask().execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void render1SelfChart(View view) {
        Intent intent = new Intent(this, Display1SelfChartActivity.class);
        intent.putExtra(STREAM_ID, streamFor1Self.getId());
        intent.putExtra(READ_TOKEN, streamFor1Self.getReadToken());
        intent.putExtra(OBJECT_TAGS, getCommaSeparatedListString(WeatherDatum.OBJECT_TAGS));
        intent.putExtra(ACTION_TAGS, getCommaSeparatedListString(WeatherDatum.ACTION_TAGS));
        intent.putExtra(AGGREGATION, "mean");
        intent.putExtra(PROPERTY, (String) WeatherDatum.PROPERTIES.keySet().toArray()[0]);
        startActivity(intent);
    }

    private static String getCommaSeparatedListString(List<String> list) {
        StringBuilder commaSeparatedListString = new StringBuilder();

        for(String string : list) {
            commaSeparatedListString.append(string).append(",");
        }
        return commaSeparatedListString.substring(0, commaSeparatedListString.length() - 1);
    }

    private class MainTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog progress = new ProgressDialog(DisplayWeatherActivity.this);

        @Override
        protected Void doInBackground(Void... params) {

            lib1SelfClient = get1SelfClient();
            Log.d("maintask", "created 1Self client");
            weatherClient = getWeatherClient();
            Log.d("maintask", "created weather client");

            getWeatherDataForLast8Days();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setTitle("Loading");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setMessage("Please wait...");
            progress.show();
            Log.d("maintask", "Started progress bar");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progress.dismiss();
            Log.d("maintask", "Stopped progress bar");

            cityAndCountryTextView.setText(getCityAndCountryParam());
        }
    }

    private void getWeatherDataForLast8Days() {
        DateTime today = new DateTime();
        DateTime eightDaysBeforeToday = today.minusDays(8);

        String cityAndCountryParam = getCityAndCountryParam();
        int numberOfDays = 2;
        DateTime start = eightDaysBeforeToday;
        while(start.isBefore(today)) {
            WeatherResponse weatherResponse = weatherClient.weatherFor(cityAndCountryParam, getEpoch(start),
                    getEpoch(start = start.plusDays(numberOfDays)), "hour");
            sendWeatherEventsTo1Self(weatherResponse);
        }
    }

    private void sendWeatherEventsTo1Self(WeatherResponse weatherResponse) {
        List<Event> events = new ArrayList<>();
        for (final WeatherDatum weatherDatum : weatherResponse.getWeatherData()) {
            events.add(weatherDatum.to1SelfEvent());
        }

        int averageBatchSize = 40;
        int totalNumberOfEvents = events.size();
        int lastBatchSize = totalNumberOfEvents % averageBatchSize;
        for(int i = 0; i <= totalNumberOfEvents; i += averageBatchSize) {
            int currentBatchSize = !isLastBatch(totalNumberOfEvents, lastBatchSize, i)
                    ? averageBatchSize : lastBatchSize;
            sendEventBatch(events.subList(i, i + currentBatchSize));
        }
    }

    private void sendEventBatch(List<Event> eventBatch) {
        lib1SelfClient.sendEventBatch(eventBatch, streamFor1Self.getId(),
                streamFor1Self.getWriteToken());
    }

    private String getCityAndCountryParam() {
        return city + ", " + country.toLowerCase();
    }

    private static long getEpoch(DateTime instant) {
        return instant.getMillis() / 1000;
    }

    private static boolean isLastBatch(int totalNumberOfEvents, int lastBatchSize, int i) {
        return totalNumberOfEvents - i == lastBatchSize;
    }

    public static OpenWeatherMapClient getWeatherClient() {
        return ServiceGenerator.createService(OpenWeatherMapClient.class,
                OpenWeatherMapClient.API_URL);
    }

    public static Lib1SelfClient get1SelfClient() {
        return ServiceGenerator.createService(Lib1SelfClient.class,
                Lib1SelfClient.API_URL);
    }
}
