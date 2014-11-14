package com.example.chowdown.network;

import android.app.Activity;
import android.util.Log;

import com.example.chowdown.models.LunchEvent;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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

    public void postToParse(LunchEvent createdLunchEvent) {

        JSONArray attendees = new JSONArray(Arrays.asList(createdLunchEvent.eventAttendees.toArray()));

        ParseObject testObject = new ParseObject("LunchEvent");
        testObject.put("startDate", createdLunchEvent.startDate.toDate());
        testObject.put("eventDescription", createdLunchEvent.description);
        testObject.put("endDate", createdLunchEvent.endDate.toDate());
        testObject.put("voteDate", createdLunchEvent.votingDate.toDate());
        testObject.put("attendees", attendees);
        testObject.put("topRestaurant", "Lily Thai");
        testObject.put("objectID", "10103930");
        testObject.saveInBackground();
    }

//    public String testLunchQuery() {
    public List<ParseObject> getLunchEvents() {
        List<ParseObject> parseObjectList = null;
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("LunchEvent");
        ArrayList<ParseObject> upcomingEvents = new ArrayList<ParseObject>();

        try {
         parseObjectList = query.find();

            //filtering just for events that have not past:
            for (ParseObject x: parseObjectList) {
                Date endDate = new Date();
                Date currentDate = new Date();
                Log.d("LOG_TAG", "currentDate is " + currentDate);

                endDate = x.getDate("endDate");
                Log.d("LOG_TAG", "endDate is " + endDate);
                if(endDate.after(currentDate)) {
                    upcomingEvents.add(x);
                }
            }
            for (ParseObject y: upcomingEvents) {
                Log.d("LOG_TAG","upcomingEvents includes " + y.getString("eventDescription"));
            }
        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        return upcomingEvents;
    }
}
