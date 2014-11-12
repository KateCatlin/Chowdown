package com.example.chowdown.network;

import android.app.Activity;

import com.example.chowdown.models.Vote;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.HashMap;

/**
 * Created by Ken on 11/10/14.
 */
public class ParsePutter {

    private final static String APPLICATION_ID = "hQ5iOAVCIZ4BCepP1zco5r1HcoTp0uuvQUhLgUyX";
    private final static String CLIENT_KEY = "Hi4IYWhFI3L7EJLaX5KIRTTJvlt6DvBQHSDSTKgS";
    Activity currentActivity;

    public ParsePutter(Activity currentActivity) {
        Parse.initialize(currentActivity, APPLICATION_ID, CLIENT_KEY);
        this.currentActivity = currentActivity;
    }

    public void saveVote(Vote vote) {
        HashMap<String, String> restaurantIds = new HashMap<String, String>();
        restaurantIds.put("Slice", "A1ItP7AEuy");
        restaurantIds.put("Orchid Thai", "doLgBRhEzo");
        restaurantIds.put("TAQO", "YqLy4jHA2T");;

        ParseObject parseVoteObject = new ParseObject("Vote");
//        parseVoteObject.put("userId", "Fakey McFakerson");

        //need a way to get Restaurants' object ids first
        parseVoteObject.put("vote1", ParseObject.createWithoutData("Restaurant", restaurantIds.get(vote.getFirstChoice())));
        parseVoteObject.put("vote2", ParseObject.createWithoutData("Restaurant", restaurantIds.get(vote.getSecondChoice())));
        parseVoteObject.put("vote3", ParseObject.createWithoutData("Restaurant", restaurantIds.get(vote.getThirdChoice())));
        parseVoteObject.put("user", ParseUser.getCurrentUser());

        parseVoteObject.put("voteForLunch", ParseObject.createWithoutData("LunchEvent", vote.getLunchId()));
        parseVoteObject.saveInBackground();
    }


}
