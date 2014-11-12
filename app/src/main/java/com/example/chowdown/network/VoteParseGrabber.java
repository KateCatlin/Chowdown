package com.example.chowdown.network;

import android.app.Activity;
import android.util.Log;

import com.example.chowdown.controllers.VoteResultsReadyListener;
import com.google.common.collect.ArrayListMultimap;
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

    private VoteResultsReadyListener listener;

    public VoteParseGrabber(VoteResultsReadyListener listener, Activity currentActivity) {
        Parse.initialize(currentActivity, APPLICATION_ID, CLIENT_KEY);
        this.listener = listener;
    }

    //List<ParseObject> parseObjectList = null;
    //HashMap<String, ArrayList<String>> voteResultHashMap = null;
    Multimap<String, String> voteResultsMultimap = ArrayListMultimap.create();

    private static String APPLICATION_ID = "hQ5iOAVCIZ4BCepP1zco5r1HcoTp0uuvQUhLgUyX";
    private static String CLIENT_KEY = "Hi4IYWhFI3L7EJLaX5KIRTTJvlt6DvBQHSDSTKgS";
    public static final String CHOSEN_LUNCH_EVENT_ID = "CHOSEN_LUNCH_EVENT_ID";
    public static final String ORCHID_THAI_OBJECT_ID = "doLgBRhEzo";
    public static final String TAQO_OBJECT_ID = "YqLy4jHA2T";
    public static final String SLICE_OBJECT_ID = "A1ItP7AEuy";

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

                    for (ParseObject vote : voteResults) {
                        ParseObject vote1 = vote.getParseObject("vote1");
                        String firstChoiceName = vote1.getString("name");
                        Log.d("firstChoiceName", firstChoiceName);
                        voteResultsMultimap.put("firstChoice", firstChoiceName);

                        ParseObject vote2 = vote.getParseObject("vote2");
                        String secondChoiceName = vote2.getString("name");
                        Log.d("secondChoiceName", secondChoiceName);
                        voteResultsMultimap.put("secondChoice", secondChoiceName);

                        ParseObject vote3 = vote.getParseObject("vote3");
                        String thirdChoiceName = vote3.getString("name");
                        Log.d("thirdChoiceName", thirdChoiceName);
                        voteResultsMultimap.put("thirdChoice", thirdChoiceName);

                        Log.d("voteResultsMap", voteResultsMultimap.toString());
                    }
                }
            }
        });
        Log.d("voteResultsMap", voteResultsMultimap.toString());
        return voteResultsMultimap;
    }

}
