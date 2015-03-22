package com.equalexperts.weather1self.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.equalexperts.weather1self.R;
import com.equalexperts.weather1self.model.WeatherSource;
import com.equalexperts.weather1self.response.lib1Self.Stream;
import com.equalexperts.weather1self.service.Lib1SelfClient;
import com.equalexperts.weather1self.service.ServiceGenerator;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class EnterCityDetailsActivity extends ActionBarActivity {

    public static final String CITY = "com.equalexperts.weather1Self.CITY";
    public static final String COUNTRY = "com.equalexperts.weather1Self.COUNTRY";
    public static final String STREAM_DETAILS = "com.equalexperts.weather1Self.STREAM_DETAILS";
    public static final String WEATHER_SOURCE = "com.equalexperts.weather1Self.WEATHER_SOURCE";

    private Lib1SelfClient lib1SelfClient;
    private Stream streamFor1Self;

    @InjectView(R.id.city_txt)
    EditText cityTxt;

    @InjectView(R.id.country_txt)
    EditText countryTxt;

    @InjectView(R.id.weather_source_radio_group)
    RadioGroup weatherSourceRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_city_details);
        ButterKnife.inject(this);

        lib1SelfClient = get1SelfClient();
        new Get1SelfStreamTask().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enter_city_details, menu);
        return true;
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

    public void sendDetails(View view) {
        Intent intent = new Intent(this, DisplayWeatherActivity.class);
        intent.putExtra(CITY, cityTxt.getText().toString());
        intent.putExtra(COUNTRY, countryTxt.getText().toString());
        intent.putExtra(STREAM_DETAILS, new String[]{streamFor1Self.getId(),
                streamFor1Self.getReadToken(), streamFor1Self.getWriteToken()});
        intent.putExtra(WEATHER_SOURCE, getWeatherSource(weatherSourceRadioGroup
                .getCheckedRadioButtonId()));
        startActivity(intent);
    }

    private WeatherSource getWeatherSource(int checkedRadioButtonId) {
        switch (checkedRadioButtonId) {
            case R.id.wunderground_com_radio_btn:
                return WeatherSource.WU;
            case R.id.openweathermap_org_radio_btn:
                return WeatherSource.OWM;
            default:
                return WeatherSource.WU;
        }
    }

    private class Get1SelfStreamTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            create1SelfStream();
            return null;
        }
    }

    private void create1SelfStream() {
        streamFor1Self = lib1SelfClient.createStream();
    }

    private static Lib1SelfClient get1SelfClient() {
        return ServiceGenerator.createService(Lib1SelfClient.class,
                Lib1SelfClient.API_BASE_URL);
    }
}
