package com.example.chowdown.network;

import android.util.Log;

import com.example.chowdown.controllers.VoteResultsListener;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mattlauer on 2014-11-10.
 */
public class VoteCalculator {

    private VoteResultsListener listener;

    public VoteCalculator(VoteResultsListener listener) {

        this.listener = listener;
    }

    Multimap<String, Integer> voteCollectionMultimap = ArrayListMultimap.create();
    Multimap<String, Integer> voteResultsMultimap = HashMultimap.create();
    HashMap<String, Integer> voteResultsHashMap = new HashMap<String, Integer>();

    private static String APPLICATION_ID = "hQ5iOAVCIZ4BCepP1zco5r1HcoTp0uuvQUhLgUyX";
    private static String CLIENT_KEY = "Hi4IYWhFI3L7EJLaX5KIRTTJvlt6DvBQHSDSTKgS";
    public static final String CHOSEN_LUNCH_EVENT_ID = "CHOSEN_LUNCH_EVENT_ID";
    public static final String ORCHID_THAI_OBJECT_ID = "doLgBRhEzo";
    public static final String TAQO_OBJECT_ID = "YqLy4jHA2T";
    public static final String SLICE_OBJECT_ID = "A1ItP7AEuy";

    public void calculateWinner(final String lunchEventID) {

        // This method grabs all the votes for a particular lunchEvent ID and calculates a winner.
        // It assigns a score to a restaurant based on its rank.
        // It then finds the restaurant with the top score and updates the lunchEvent with the topRestaurant.
        // In the event of a tie, appears to give it to the top restaurant submitted first.

        ParseQuery<ParseObject> voteQuery = ParseQuery.getQuery("Vote");
        voteQuery.whereEqualTo("relatedLunch", ParseObject.createWithoutData("LunchEvent", lunchEventID));
        voteQuery.include("restaurantChoice");
        voteQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> voteResults, ParseException e) {
                if (e != null) {
                    Log.d("findVotes", "The request failed." + e.toString());
                } else {
                    Log.d("findVotes", "Found the votes.");
                    //Log.d("results", voteResults.toString());
                    for (ParseObject vote : voteResults) {
                        ParseObject restaurantChoice = vote.getParseObject("restaurantChoice");

                        String restaurantChoiceName = restaurantChoice.getString("name");

                        voteResultsHashMap.put(restaurantChoiceName, 0);

                        int restaurantChoiceRank = vote.getInt("rank");

                        int firstScore = 10;
                        int secondScore = 5;
                        int thirdScore = 1;

                        if (restaurantChoiceRank == 1) {
                            voteResultsHashMap.put(restaurantChoiceName, voteResultsHashMap.get(restaurantChoiceName) + firstScore);
                        }
                        if (restaurantChoiceRank == 2) {
                            voteResultsHashMap.put(restaurantChoiceName, voteResultsHashMap.get(restaurantChoiceName) + secondScore);
                        }
                        if (restaurantChoiceRank == 3) {
                            voteResultsHashMap.put(restaurantChoiceName, voteResultsHashMap.get(restaurantChoiceName) + thirdScore);
                        }
                    }
                    Map.Entry<String, Integer> maxEntry = null;
                    for (Map.Entry<String, Integer> entry : voteResultsHashMap.entrySet()) {
                        if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                            maxEntry = entry;
                        }
                    }

                    ParseObject parseLunchObject = ParseObject.createWithoutData("LunchEvent", lunchEventID);
                    parseLunchObject.put("topRestaurant", maxEntry.getKey());
                    parseLunchObject.put("votingEnded", true);
                    parseLunchObject.saveInBackground();
                }
            }
        });

    }
}
