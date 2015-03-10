package com.equalexperts.weather1self.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.equalexperts.weather1self.R;
import com.equalexperts.weather1self.response.Stream;
import com.equalexperts.weather1self.service.Lib1SelfClient;
import com.equalexperts.weather1self.service.OpenWeatherMapClient;
import com.equalexperts.weather1self.service.ServiceGenerator;

import java.util.Arrays;


public class EnterCityDetailsActivity extends ActionBarActivity {

    public static final String CITY = "com.equalexperts.weather1Self.CITY";
    public static final String COUNTRY = "com.equalexperts.weather1Self.COUNTRY";
    public static final String STREAM_DETAILS = "com.equalexperts.weather1Self.STREAM_DETAILS";

    private Lib1SelfClient lib1SelfClient;
    private Stream streamFor1Self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_city_details);
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
        EditText city = (EditText) findViewById(R.id.city);
        intent.putExtra(CITY, city.getText().toString());
        EditText country = (EditText) findViewById(R.id.country);
        intent.putExtra(COUNTRY, country.getText().toString());
        intent.putExtra(STREAM_DETAILS, new String[]{streamFor1Self.getId(),
                streamFor1Self.getReadToken(), streamFor1Self.getWriteToken()});
        startActivity(intent);
    }

    private class Get1SelfStreamTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            create1SelfStream();
            return null;
        }
    }

    private void create1SelfStream() {
        streamFor1Self = lib1SelfClient.getStream();
    }

    private static Lib1SelfClient get1SelfClient() {
        return ServiceGenerator.createService(Lib1SelfClient.class,
                Lib1SelfClient.API_URL);
    }
}
