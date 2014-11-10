package com.example.chowdown.models;

import com.parse.ParseObject;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;

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




        //eventID = mParseObject.getString("objectId");
        eventID = mParseObject.getObjectId();
        //eventID = "Test String";

        description = mParseObject.getString("eventDescription");

        fakeStartDate = mParseObject.getDate("startDate");
        startDate = new DateTime(fakeStartDate);

        fakeEndDate = mParseObject.getDate("endDate");
        endDate = new DateTime(fakeEndDate);

        fakeVotingDate = mParseObject.getDate("voteDate");
        votingDate = new DateTime(fakeVotingDate);

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

        mLunchEvent = new LunchEvent(eventID, description, startDate, endDate, votingDate, eventAttendees, topRestaurant);


        return mLunchEvent;
    }




}
