package com.example.chowdown.network;

import android.app.Activity;
import android.util.Log;

import com.example.chowdown.models.LunchEvent;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;

import org.json.JSONArray;

import java.util.ArrayList;
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

        Date currentDate = new Date();
        JSONArray attendees = new JSONArray(Arrays.asList(new String[] {"boy1", "woman2", "dog1", "cat1"}));


        ParseObject testObject = new ParseObject("LunchEvent");
        testObject.put("startDate", currentDate);
        testObject.put("endDate", currentDate);
        testObject.put("voteDate", currentDate);
        testObject.put("attendees", attendees);
        testObject.put("topRestaurant", "Lily Thai");
        testObject.saveInBackground();
    }

//    public String testLunchQuery() {
//
//    }

    public List<ParseObject> getLunchEvents(){
        List<ParseObject> parseObjectList = null;
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("LunchEvent");

        try {
         parseObjectList = query.find();
        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        return parseObjectList;
//        try {
//            Thread.sleep(10000);
//            System.out.println("I'm sleeping");
//            Log.v("I am ", "sleeping");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        for(ParseObject po: parseObjectList) {
//            System.out.println(po.getString("topRestaurant"));
//            Log.v("Returned topRestaurant", po.getString("topRestaurant"));
//        }
//
//        ArrayList<String> testList = new ArrayList<String>();
//        testList.add("string 1");
//
//        for(String po: testList) {
////            System.out.println(po.getString("topRestaurant"));
//            Log.v("Returned topRestaurant", po);
//        }
//
//        ArrayList<LunchEvent> lunchEvents = new ArrayList<LunchEvent>();
//
//        return lunchEvents;
    }

    private void grabEventIDFromParse(LunchEvent lunchEvent) {
        // Use Parse to grab the objectID for the Parse record associated with this LunchEvent.
        // Assumes the LunchEvent is already saved to Parse.
        // Then set eventID using setter method.
    }

    private void grabDatesFromParse(LunchEvent lunchEvent) {
        // Use Parse to grab all dates for a particular LunchEvent, and set them using the setter methods.
        // Assumes the LunchEvent is already saved to Parse.
    }

    private void grabEventAttendeesFromParse(LunchEvent lunchEvent) {
        // Use Parse to grab all user names associated with all votes that are tied to this LunchEvent ID.
        // Store those name Strings into the eventAttendees ArrayList.
        // Again assumes the entries have already been added to Parse.
        // Then set eventAttendees using setter method.
    }

    public void setUpLunchEvent(LunchEvent lunchEvent) {
        // Call the grab methods above to fill this LunchEvent in Java. These methods are private.
        // User should just call setUpLunchEvent() when she needs a new LunchEvent.
    }
}
