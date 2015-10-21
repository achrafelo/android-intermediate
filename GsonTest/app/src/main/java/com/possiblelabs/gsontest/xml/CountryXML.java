package com.possiblelabs.gsontest.xml;

import com.google.gson.annotations.SerializedName;

/**
 * Created by possiblelabs on 10/21/15.
 */
public class CountryXML {

    @SerializedName("@name")
    private String name;

    @SerializedName("@value")
    private int value;

    String data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CountryXML{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
