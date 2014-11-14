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

    public void testPostToParse(String lunchEventID) {
        ParseObject submitTestVote = new ParseObject("Vote");
        submitTestVote.put("userId", "FakeUser");
        submitTestVote.put("vote1", ParseObject.createWithoutData("Restaurant", ORCHID_THAI_OBJECT_ID));
        submitTestVote.put("vote2", ParseObject.createWithoutData("Restaurant", TAQO_OBJECT_ID));
        submitTestVote.put("vote3", ParseObject.createWithoutData("Restaurant", SLICE_OBJECT_ID));
        submitTestVote.put("voteForLunch", ParseObject.createWithoutData("LunchEvent", lunchEventID));

        submitTestVote.saveInBackground();
    }

    public void calculateWinner(final String lunchEventID) {

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
                    Log.d("results", voteResults.toString());
                    for (ParseObject vote : voteResults) {
                        ParseObject restaurantChoice = vote.getParseObject("restaurantChoice");

                        String restaurantChoiceName = restaurantChoice.getString("name");
                        Log.d("restaurantChoiceName", restaurantChoiceName);
                        voteResultsHashMap.put(restaurantChoiceName, 0);

                        int restaurantChoiceRank = vote.getInt("rank");
                        Log.d("restaurantChoiceRank", String.valueOf(restaurantChoiceRank));

                        if (restaurantChoiceRank == 1) {
                            voteResultsHashMap.put(restaurantChoiceName, voteResultsHashMap.get(restaurantChoiceName) + 10);
                        }
                        if (restaurantChoiceRank == 2) {
                            voteResultsHashMap.put(restaurantChoiceName, voteResultsHashMap.get(restaurantChoiceName) + 5);
                        }
                        if (restaurantChoiceRank == 3) {
                            voteResultsHashMap.put(restaurantChoiceName, voteResultsHashMap.get(restaurantChoiceName) + 1);
                        }

                        Log.d("voteResultsMap", voteResultsHashMap.toString());
                    }
                    Map.Entry<String, Integer> maxEntry = null;
                    for (Map.Entry<String, Integer> entry : voteResultsHashMap.entrySet()) {
                        if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                            maxEntry = entry;
                        }
                    }
                    Log.d("maxEntry", maxEntry.toString());

                    ParseObject parseLunchObject = ParseObject.createWithoutData("LunchEvent", lunchEventID);
                    parseLunchObject.put("topRestaurant", maxEntry.getKey());
                    parseLunchObject.put("votingEnded", true);
                    parseLunchObject.saveInBackground();
                }
            }
        });

    }

//    public ArrayList<String> getArrayListsOfRestaurantVotes(String rank) {
//        if (voteResultsMultimap == null) {
//            Log.d("NoMap", "NG");
//            return null;
//        }
//        if (rank.equals("first")) {
//            ArrayList<String> firstChoiceRestaurants = getArrayListFromCollection(voteResultsMultimap.get("firstChoice"));
//            Log.d("firstChoiceRestaurants", firstChoiceRestaurants.toString());
//            return firstChoiceRestaurants;
//        }
//        if (rank.equals("second")) {
//            ArrayList<String> secondChoiceRestaurants = getArrayListFromCollection(voteResultsMultimap.get("secondChoice"));
//            Log.d("secondChoiceRestaurants", secondChoiceRestaurants.toString());
//            return secondChoiceRestaurants;
//        }
//        if (rank.equals("third")) {
//            ArrayList<String> thirdChoiceRestaurants = getArrayListFromCollection(voteResultsMultimap.get("thirdChoice"));
//            Log.d("thirdChoiceRestaurants", thirdChoiceRestaurants.toString());
//            return thirdChoiceRestaurants;
//        }
//        return null;
//    }
//
//    public ArrayList<String> getArrayListFromCollection(Collection<String> collection) {
//        String[] stringArray = collection.toArray(new String[collection.size()]);
//        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(stringArray));
//        return arrayList;
//    }

}
