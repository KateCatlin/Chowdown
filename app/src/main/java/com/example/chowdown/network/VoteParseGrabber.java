package com.example.chowdown.network;

import android.app.Activity;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by mattlauer on 2014-11-10.
 */
public class VoteParseGrabber {

    private static String APPLICATION_ID = "hQ5iOAVCIZ4BCepP1zco5r1HcoTp0uuvQUhLgUyX";
    private static String CLIENT_KEY = "Hi4IYWhFI3L7EJLaX5KIRTTJvlt6DvBQHSDSTKgS";

    public VoteParseGrabber(Activity currentActivity) {
        Parse.initialize(currentActivity, APPLICATION_ID, CLIENT_KEY);
    }

    public List<ParseObject> getVotesByLunchID(String lunchEventID) {
        List<ParseObject> parseObjectList = null;
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Vote");

        try {
            parseObjectList = query.find();
        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        return parseObjectList;
    }
}
