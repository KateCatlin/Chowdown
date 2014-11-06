package com.example.chowdown.models;

import android.app.Activity;

import com.example.chowdown.activities.MainActivity;
import com.example.chowdown.network.LunchEventParseGrabber;
import com.parse.ParseObject;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by katecatlin on 11/6/14.
 */
public class ParseConverterObject extends ParseObject {
    LunchEvent mLunchEvent;


    public LunchEvent parseToObject(ParseObject mParseObject) {
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

        eventID = mParseObject.getString("objectID");

        description = mParseObject.getString("eventDescription");

        fakeStartDate = mParseObject.getDate("startDate");
        startDate = new DateTime(fakeStartDate);

        fakeEndDate = mParseObject.getDate("endDate");
        endDate = new DateTime(fakeEndDate);

        fakeVotingDate = mParseObject.getDate("voteDate");
        votingDate = new DateTime(fakeVotingDate);

        eventAttendees = new ArrayList<String>();
//        eventAttendees = mParseObject.getList("attendees");
        eventAttendees.add(0, mParseObject.getList("attendees").toString());

        topRestaurant = mParseObject.getString("topRestaurant");

        return mLunchEvent;
    }




}
