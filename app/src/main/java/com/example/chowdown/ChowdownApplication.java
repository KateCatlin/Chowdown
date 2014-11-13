package com.example.chowdown;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by mattlauer on 2014-11-12.
 */
public class ChowdownApplication extends Application {
    private static String APPLICATION_ID = "hQ5iOAVCIZ4BCepP1zco5r1HcoTp0uuvQUhLgUyX";
    private static String CLIENT_KEY = "Hi4IYWhFI3L7EJLaX5KIRTTJvlt6DvBQHSDSTKgS";

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);
    }
}
