package com.possiblelabs.preferencescreentest;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by possiblelabs on 9/4/15.
 */
public class SettingsActivity extends PreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
