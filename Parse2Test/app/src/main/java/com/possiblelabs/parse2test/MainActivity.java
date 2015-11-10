package com.possiblelabs.parse2test;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.lst_places)
    ListView lstPlaces;

    @Bind(R.id.txt_lat)
    EditText txtLat;

    @Bind(R.id.txt_lng)
    EditText txtLng;

    @Bind(R.id.txt_place)
    EditText txtPlace;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String lat = txtLat.getText().toString();
                String lng = txtLng.getText().toString();
                String place = txtPlace.getText().toString();

                Place oPlace = new Place(lat, lng, place);
                ParseObject pObject = oPlace.toParseObject();

                pObject.saveInBackground(afterSave);
            }
        });
    }

    private SaveCallback afterSave = new SaveCallback() {
        @Override
        public void done(ParseException e) {
            if (e != null)
                return;

            loadParseObjects();


        }
    };

    private FindCallback<ParseObject> findResult = new FindCallback<ParseObject>() {
        @Override
        public void done(List<ParseObject> places, ParseException e) {
            if (e != null)
                return;

            List<String> names = new ArrayList<String>();

            for (ParseObject po : places)
                names.add(po.get("name").toString());

            ArrayAdapter<String> placesAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, names);
            lstPlaces.setAdapter(placesAdapter);
        }
    };

    private void loadParseObjects() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Place");
        query.findInBackground(findResult);
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
