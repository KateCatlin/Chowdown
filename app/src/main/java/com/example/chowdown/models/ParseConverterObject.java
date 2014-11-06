package com.example.chowdown.models;

import android.app.Activity;
import android.util.Log;
import com.parse.ParseObject;

import com.example.chowdown.activities.MainActivity;
import com.example.chowdown.network.LunchEventParseGrabber;
import com.parse.ParseObject;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.jar.JarException;

/**
 * Created by katecatlin on 11/6/14.
 */
public class ParseConverterObject {
    String eventID;
    String description;
    Date fakeStartDate;
    DateTime startDate;
    Date fakeEndDate;
    DateTime endDate;
    Date fakeVotingDate;
    DateTime votingDate;
    ArrayList<String> eventAttendees;
    String topRestaurant;
    final String LOG_CAT = "ParseConverterObject";
    LunchEvent mLunchEvent;
    ParseObject mParseObject;


    public LunchEvent parseToObject(ParseObject mParseObject) {

        Log.d(LOG_CAT, "Made it into parseToObject");



        eventID = mParseObject.getString("objectID");
        Log.d(LOG_CAT, "eventID is " + eventID);

        description = mParseObject.getString("eventDescription");
        Log.d(LOG_CAT, "eventDescription is " + description);

        fakeStartDate = mParseObject.getDate("startDate");
        startDate = new DateTime(fakeStartDate);
        Log.d(LOG_CAT, "startDate is " + startDate);

        fakeEndDate = mParseObject.getDate("endDate");
        endDate = new DateTime(fakeEndDate);
        Log.d(LOG_CAT, "endDate is " + endDate);

        fakeVotingDate = mParseObject.getDate("voteDate");
        votingDate = new DateTime(fakeVotingDate);
        Log.d(LOG_CAT, "votingDate is " + votingDate);

        eventAttendees = new ArrayList<String>();
        JSONArray mJsonArray = (JSONArray) mParseObject.getJSONArray("attendees");
        try {
            if (mJsonArray != null) {
                int len = mJsonArray.length();
                for (int i=0;i<len;i++){
                    eventAttendees.add(mJsonArray.get(i).toString());
                }
            }
        } catch (JSONException e) {
            return null;
        }


        topRestaurant = mParseObject.getString("topRestaurant");
        Log.d(LOG_CAT, "topRestaurant is " + topRestaurant);

        mLunchEvent = new LunchEvent(eventID, description, startDate, endDate, votingDate, eventAttendees, topRestaurant);


        return mLunchEvent;
    }




}
