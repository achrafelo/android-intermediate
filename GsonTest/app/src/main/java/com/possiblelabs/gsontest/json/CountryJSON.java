package com.possiblelabs.gsontest.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by possiblelabs on 10/21/15.
 */
public class CountryJSON {

    @SerializedName("iso2")
    private String iso2;

    @SerializedName("name")
    private String name;

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CountryJSON{" +
                "iso2='" + iso2 + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
