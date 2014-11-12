package com.example.chowdown.network;

import android.app.Activity;
import android.util.Log;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by mattlauer on 2014-11-10.
 */
public class VoteParseGrabber {

    //List<ParseObject> parseObjectList = null;
    //HashMap<String, ArrayList<String>> voteResultHashMap = null;
    Multimap<String, String> voteResultsMultimap = HashMultimap.create();

    private static String APPLICATION_ID = "hQ5iOAVCIZ4BCepP1zco5r1HcoTp0uuvQUhLgUyX";
    private static String CLIENT_KEY = "Hi4IYWhFI3L7EJLaX5KIRTTJvlt6DvBQHSDSTKgS";
    public static final String CHOSEN_LUNCH_EVENT_ID = "CHOSEN_LUNCH_EVENT_ID";
    public static final String ORCHID_THAI_OBJECT_ID = "doLgBRhEzo";
    public static final String TAQO_OBJECT_ID = "YqLy4jHA2T";
    public static final String SLICE_OBJECT_ID = "A1ItP7AEuy";

    public VoteParseGrabber(Activity currentActivity) {
        Parse.initialize(currentActivity, APPLICATION_ID, CLIENT_KEY);
    }

    public void testPostToParse(String lunchEventID) {
        ParseObject submitTestVote = new ParseObject("Vote");
        submitTestVote.put("userId", "FakeUser");
        submitTestVote.put("vote1", ParseObject.createWithoutData("Restaurant", ORCHID_THAI_OBJECT_ID));
        submitTestVote.put("vote2", ParseObject.createWithoutData("Restaurant", TAQO_OBJECT_ID));
        submitTestVote.put("vote3", ParseObject.createWithoutData("Restaurant", SLICE_OBJECT_ID));
        submitTestVote.put("voteForLunch", ParseObject.createWithoutData("LunchEvent", lunchEventID));

        submitTestVote.saveInBackground();
    }

//    public List<ParseObject> getVotesByLunchID(String lunchEventID) {
    public Multimap<String, String> getVotesByLunchID(String lunchEventID) {
        ParseQuery<ParseObject> voteQuery = ParseQuery.getQuery("Vote");
        voteQuery.whereEqualTo("voteForLunch", ParseObject.createWithoutData("LunchEvent", lunchEventID));
        voteQuery.include("vote1");
        voteQuery.include("vote2");
        voteQuery.include("vote3");

        voteQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> voteResults, ParseException e) {
                if (e != null) {
                    Log.d("findVotes", "The request failed.");
                } else {
                    Log.d("findVotes", "Found the votes.");
                    Log.d("results", voteResults.toString());
                }
                for (ParseObject vote : voteResults) {
                    ParseObject vote1 = vote.getParseObject("vote1");
                    //Log.d("vote1", vote1.getString("name"));
                    //parseObjectList.add(vote1);
                    voteResultsMultimap.put("firstChoice", vote1.getString("vote1"));
                    ParseObject vote2 = vote.getParseObject("vote2");
                    //Log.d("vote2", vote2.getString("name"));
                    //parseObjectList.add(vote1);
                    voteResultsMultimap.put("secondChoice", vote1.getString("vote2"));
                    ParseObject vote3 = vote.getParseObject("vote3");
                    //Log.d("vote3", vote3.getString("name"));
                    //parseObjectList.add(vote1);
                    voteResultsMultimap.put("thirdChoice", vote1.getString("vote3"));
                }
            }
        });
        Log.d("voteResultsMap", voteResultsMultimap.toString());
        return voteResultsMultimap;
    }

}
