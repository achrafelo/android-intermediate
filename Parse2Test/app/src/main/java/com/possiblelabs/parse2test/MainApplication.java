package com.possiblelabs.parse2test;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by possiblelabs on 11/3/15.
 */
public class MainApplication extends Application {

    public void onCreate(){
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "MN9tdEaWUnEidgptlNiHHc5ZEKJQfibQjxj1CXFK", "4ZyNa1zjvjUp0tzHIqqthe8sJuKk571ukBdMhSgS");

    }
}
