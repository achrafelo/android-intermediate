package com.possiblelabs.gsontest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.possiblelabs.gsontest.json.CountriesJSON;
import com.possiblelabs.gsontest.json.CountryJSON;
import com.possiblelabs.gsontest.xml.CountryXML;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout llRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        llRes = (LinearLayout) findViewById(R.id.ll_res);
    }

    public void doBtnJSON(View view) {
        llRes.removeAllViews();
        CountriesJSON countries = JsonHelper.loadFromJSON(this);
        for (CountryJSON country : countries.getCountries()) {
            LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextView tv = new TextView(this);
            tv.setLayoutParams(lparams);
            tv.setText(country.getIso2() + "=> " + country.getName() + "");
            llRes.addView(tv);
        }
    }

    public void doBtnXML(View view) {
        llRes.removeAllViews();
        List<CountryXML> countryList = XmlHelper.loadFromXML(this);
        for (CountryXML country : countryList) {
            LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextView tv = new TextView(this);
            tv.setLayoutParams(lparams);
            tv.setText(country.getValue() + "=> " + country.getName() + "");
            llRes.addView(tv);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
