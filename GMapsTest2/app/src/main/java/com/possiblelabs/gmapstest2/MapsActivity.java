package com.possiblelabs.gmapstest2;

import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int currentIndex = 0;
    private Point[] points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        points = PointHelper.listPoints();
    }

    public void doNext(View view) {

        if (currentIndex < points.length - 1)
            currentIndex++;
        else
            currentIndex = 0;

        updateMarkerCurrentIndex();

    }


    public void doBack(View view) {

        if (currentIndex > 0)
            currentIndex--;
        else
            currentIndex = points.length - 1;

        updateMarkerCurrentIndex();
    }

    private void updateMarkerCurrentIndex() {
        Point point = points[currentIndex];
        CameraUpdate cu = CameraUpdateFactory.newLatLng(point.getLatLng());
        mMap.animateCamera(cu);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        mMap.setMapType(Ma);


        for (Point p : points) {
            Marker m = mMap.addMarker(p.toMarker());
            m.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
            m.showInfoWindow();
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(points[currentIndex].getLatLng(), 16f));


    }
}
