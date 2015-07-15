package com.possiblelabs.googlemapstest;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private final static int MAX_PLACE = 5;
    private int indexPlace = 0;
    private TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        txtTitle = (TextView) findViewById(R.id.txt_title);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        for (MyPlace place : MapsActivity.getAll()) {
            mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getName()));
        }
        animateToPlace(indexPlace);
    }

    private void animateToPlace(int index) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MapsActivity.getAll()[index].getLatLng(), 17f));
        txtTitle.setText(MapsActivity.getAll()[index].getName());
    }

    public void doBtnNext(View view) {
        if (mMap != null) {

            if (indexPlace < MAX_PLACE - 1)
                indexPlace++;
            else
                indexPlace = 0;

            animateToPlace(indexPlace);
        }
    }

    public void doBtnPrev(View view) {
        if (mMap != null) {

            if (indexPlace > 0)
                indexPlace--;
            else
                indexPlace = MAX_PLACE - 1;

            animateToPlace(indexPlace);
        }

    }


    private static MyPlace[] places;

    private static MyPlace[] getAll() {
        if (places == null) {
            places = new MyPlace[MAX_PLACE];
            places[0] = new MyPlace("Francia", 48.856614, 2.352222);
            places[1] = new MyPlace("Bolivia", 22, 3.352222);
            places[2] = new MyPlace("Israel", 31.768319, 35.213710);
            places[3] = new MyPlace("China", 39.904211, 116.407395);
            places[4] = new MyPlace("Japon", 35.709026, 139.731992);
        }
        return places;
    }
}
