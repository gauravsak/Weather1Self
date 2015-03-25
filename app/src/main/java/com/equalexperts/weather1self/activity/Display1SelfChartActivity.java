package com.equalexperts.weather1self.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.equalexperts.weather1self.R;
import com.equalexperts.weather1self.service.Lib1SelfClient;

import butterknife.ButterKnife;

public class Display1SelfChartActivity extends ActionBarActivity {

//    @InjectView(R.id.chartWebView)
//    WebView chartWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_1self_chart);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        String streamId = intent.getStringExtra(DisplayWeatherActivity.STREAM_ID);
        String readToken = intent.getStringExtra(DisplayWeatherActivity.READ_TOKEN);
        String objectTags = intent.getStringExtra(DisplayWeatherActivity.OBJECT_TAGS);
        String actionTags = intent.getStringExtra(DisplayWeatherActivity.ACTION_TAGS);
        String aggregation = intent.getStringExtra(DisplayWeatherActivity.AGGREGATION);
        String property = intent.getStringExtra(DisplayWeatherActivity.PROPERTY);
        final String chartUrl = Lib1SelfClient.API_BASE_URL + "/streams/" + streamId + "/events/" + objectTags + "/"
                + actionTags + "/" + aggregation + "(" + property + ")/"
                + "daily/barchart?readToken=" + readToken;
        Log.d("renderChart", "1Self chart URL : " + chartUrl);

        String barChartURI = intent.getStringExtra(DisplayWeatherActivity.BAR_CHART_URI);
        Log.d("renderBarChart", "bar chart from Weather1Self API : " + barChartURI);

        /* FIXME: Not working due to some bug with WebView, hence, delegating to browser activity */
        /*
        chartWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        chartWebView.post(new Runnable() {

            @Override
            public void run() {
                chartWebView.loadUrl(chartUrl, Collections.<String, String>emptyMap());
            }
        });
        */

//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(chartUrl));
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(barChartURI));
        startActivity(browserIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display1_self_chart, menu);
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
}