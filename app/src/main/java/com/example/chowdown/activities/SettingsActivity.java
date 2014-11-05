package com.example.chowdown.activities;

import android.app.Activity;
import android.os.Bundle;

import com.example.chowdown.fragments.SettingsFragment;

/**
 * Created by Borham on 11/4/14.
 */
public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}