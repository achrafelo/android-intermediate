package com.possiblelabs.googlemapstest;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by possiblelabs on 7/14/15.
 */
public class MyPlace {

    private String name;
    private LatLng latLng;

    public MyPlace(String name, double lat, double lng) {
        this.name = name;
        this.latLng = new LatLng(lat, lng);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyPlace{");
        sb.append("name='").append(name).append('\'');
        sb.append(", latLng=").append(latLng.latitude).append(",").append(latLng.longitude);
        sb.append('}');
        return sb.toString();
    }
}
