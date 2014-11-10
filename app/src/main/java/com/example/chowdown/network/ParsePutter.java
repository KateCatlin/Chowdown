package com.example.chowdown.network;

import android.app.Activity;

import com.example.chowdown.models.Vote;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Ken on 11/10/14.
 */
public class ParsePutter {

    private final static String APPLICATION_ID = "hQ5iOAVCIZ4BCepP1zco5r1HcoTp0uuvQUhLgUyX";
    private final static String CLIENT_KEY = "Hi4IYWhFI3L7EJLaX5KIRTTJvlt6DvBQHSDSTKgS";

    public ParsePutter(Activity currentActivity) {
        Parse.initialize(currentActivity, APPLICATION_ID, CLIENT_KEY);
    }

    public void saveVote(Vote vote) {
        ParseObject parseVoteObject = new ParseObject("Vote");
        parseVoteObject.put("userId", "Fakey McFakerson");

        //need a way to get Restaurants' object ids first
//        parseVoteObject.put("vote1", ParseObject.createWithoutData("Restaurant", vote.getFirstChoice()));
//        parseVoteObject.put("vote2", ParseObject.createWithoutData("Restaurant", vote.getSecondChoice()));
//        parseVoteObject.put("vote3", ParseObject.createWithoutData("Restaurant", vote.getThirdChoice()));

        parseVoteObject.put("voteForLunch", ParseObject.createWithoutData("LunchEvent", vote.getLunchId()));
        parseVoteObject.saveInBackground();
    }


}
