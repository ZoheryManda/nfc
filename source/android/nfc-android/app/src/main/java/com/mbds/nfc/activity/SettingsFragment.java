package com.mbds.nfc.activity;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.mbds.nfc.R;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Load preference data from XML
        addPreferencesFromResource(R.xml.settings);
    }
}