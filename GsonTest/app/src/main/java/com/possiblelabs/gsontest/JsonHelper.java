package com.possiblelabs.gsontest;

import android.content.Context;

import com.google.gson.Gson;
import com.possiblelabs.gsontest.json.CountriesJSON;

/**
 * Created by possiblelabs on 10/21/15.
 */
public class JsonHelper {

    public static CountriesJSON loadFromJSON(Context context) {

        Gson gson = new Gson();
        String json = AssetsHelper.loadFileFromAsset(context, "countries.json");
        return gson.fromJson(json, CountriesJSON.class);
    }
}
