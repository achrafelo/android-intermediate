package com.possiblelabs.activeandroidtest;

import com.activeandroid.ActiveAndroid;

/**
 * Created by possiblelabs on 7/21/15.
 */
public class MainApplication extends com.activeandroid.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
