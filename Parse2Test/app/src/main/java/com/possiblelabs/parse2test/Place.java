package com.possiblelabs.parse2test;

import com.parse.ParseObject;

/**
 * Created by possiblelabs on 11/3/15.
 */
public class Place {

    private String lat;
    private String lng;
    private String name;

    public Place(String lat, String lng, String name) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;
    }


    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParseObject toParseObject() {
        ParseObject po = new ParseObject("Place");
        po.put("lat", lat);
        po.put("lng", lng);
        po.put("name", name);
        return po;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Place{");
        sb.append("lat='").append(lat).append('\'');
        sb.append(", lng='").append(lng).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
