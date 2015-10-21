package com.possiblelabs.gsontest;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by possiblelabs on 10/21/15.
 */
public class AssetsHelper {

    public static String loadFileFromAsset(Context context, String filename) {
        String json;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
