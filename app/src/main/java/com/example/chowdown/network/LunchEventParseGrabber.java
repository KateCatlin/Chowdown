package com.example.chowdown.network;

import android.app.Activity;

import com.example.chowdown.models.LunchEvent;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by mattlauer on 2014-11-04.
 */
public class LunchEventParseGrabber {

    private static String APPLICATION_ID = "hQ5iOAVCIZ4BCepP1zco5r1HcoTp0uuvQUhLgUyX";
    private static String CLIENT_KEY = "Hi4IYWhFI3L7EJLaX5KIRTTJvlt6DvBQHSDSTKgS";

    public LunchEventParseGrabber(Activity activity) {
        Parse.initialize(activity, APPLICATION_ID, CLIENT_KEY);
    }

    public void testPostToParse() {
        ParseObject testObject = new ParseObject("ExtractedParceCall");
        testObject.put("didthiswork", "yes");
        testObject.saveInBackground();
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
