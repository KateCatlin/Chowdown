package com.example.chowdown.network;

import android.app.Activity;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.joda.time.DateTime;
import org.json.JSONArray;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by mattlauer on 2014-11-04.
 */
public class LunchEventParseGrabber {

    private static String APPLICATION_ID = "hQ5iOAVCIZ4BCepP1zco5r1HcoTp0uuvQUhLgUyX";
    private static String CLIENT_KEY = "Hi4IYWhFI3L7EJLaX5KIRTTJvlt6DvBQHSDSTKgS";

    public LunchEventParseGrabber(Activity currentActivity) {
        Parse.initialize(currentActivity, APPLICATION_ID, CLIENT_KEY);
    }

    public void testPostToParse() {

        JSONArray attendees = new JSONArray(Arrays.asList(new String[] {"boy1", "woman2", "dog1", "cat1"}));

        DateTime dt = new DateTime(2014, 11, 15, 4, 7, 20);
        Date dte = dt.toDate();


        ParseObject testObject = new ParseObject("LunchEvent");
        testObject.put("startDate", dte);
        testObject.put("eventDescription", "PARTY");
        testObject.put("endDate", dte);
        testObject.put("voteDate", dte);
        testObject.put("attendees", attendees);
        testObject.put("topRestaurant", "Lily Thai");
        testObject.put("objectID", "10103930");
        testObject.saveInBackground();
    }

    public List<ParseObject> getLunchEvents() {
        List<ParseObject> parseObjectList = null;
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("LunchEvent");

        try {
            parseObjectList = query.find();
        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        return parseObjectList;
    }
}
