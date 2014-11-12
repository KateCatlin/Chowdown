package com.example.chowdown.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chowdown.R;
import com.example.chowdown.adapters.StableArrayAdapter;
import com.example.chowdown.controllers.VoteResultsReadyListener;
import com.example.chowdown.models.LunchEvent;
import com.example.chowdown.models.Vote;
import com.example.chowdown.network.ParsePutter;
import com.example.chowdown.network.VoteParseGrabberAndCalculator;
import com.example.chowdown.views.DynamicListView;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class RankingActivity extends Activity implements VoteResultsReadyListener {


    public static final String CHOSEN_LUNCH_EVENT_2 = "CHOSEN_LUNCH_EVENT_ID";
    public static final String ORCHID_THAI_OBJECT_ID = "doLgBRhEzo";
    public static final String TAQO_OBJECT_ID = "YqLy4jHA2T";
    public static final String SLICE_OBJECT_ID = "A1ItP7AEuy";
    public static final String POST_VOTE_ACTIVITY_KEY = "POST_VOTE_ACTIVITY_KEY";
    String lunchEventID;
    public static final String PASS_TO_RANKING_KEY = "PASS_TO_RANKING_KEY";
    public static final String PASS_TO_POST_VOTE_ACTIVITY_KEY = "PASS_TO_POST_VOTE_ACTIVITY_KEY";

    //List<ParseObject> pOL;
    Multimap<String, String> voteResultsMultimap = ArrayListMultimap.create();
    ParseObject testLunchEvent;

    StableArrayAdapter restaurantAdaptor;

    VoteParseGrabberAndCalculator voteParseGrabberAndCalculator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        final LunchEvent chosenLunchEvent = (LunchEvent) getIntent().getParcelableExtra(PASS_TO_RANKING_KEY);

        Intent intent = getIntent();
        lunchEventID = chosenLunchEvent.getEventID();
        Log.d("LOG_TAG", "EventID is " + chosenLunchEvent.getEventID());
        TextView testTextView1 = (TextView) findViewById(R.id.title_text_view);
        testTextView1.setText(lunchEventID);

        voteParseGrabberAndCalculator = new VoteParseGrabberAndCalculator(this, this);

        //voteParseGrabber.testPostToParse(lunchEventID);

        //
        // THIS CODE GRABS ALL THE VOTES SUBMITTED FOR A PARTICULAR LUNCH EVENT
        // It should probably belong in a different place, some kind of utility.
        // We'll move it later
        voteParseGrabberAndCalculator.calculateWinner(lunchEventID);
        // DELAY
//        try {
//            Thread.sleep(5000);
        ArrayList<String> firstChoiceRestaurants = voteParseGrabberAndCalculator.getArrayListsOfRestaurantVotes("first");
        Log.d("firstChoiceRestaurants", firstChoiceRestaurants.toString());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            Log.d("NoSleep", "NG");
//        }
        //

        // WHY DOES THE LOG HAPPEN BEFORE I GET MY RESULTS?! HOW CAN I MAKE IT HAPPEN AFTERWARD?


        // END OF VOTE MANIPULATION CODE
        //

        DynamicListView topRestaurantsListView = (DynamicListView) findViewById(R.id.ranked_restaurants_listview);

        ArrayList<String> restaurants = new ArrayList<String>();
        restaurants.add("Slice");
        restaurants.add("Orchid Thai");
        restaurants.add("TAQO");
        restaurantAdaptor = new StableArrayAdapter(this, R.layout.list_item_restaurant, restaurants);

        topRestaurantsListView.setCheeseList(restaurants);
        topRestaurantsListView.setAdapter(restaurantAdaptor);
        topRestaurantsListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        Button submitButton = (Button) findViewById(R.id.submit_vote_button);
        final Activity thisActivity = this;

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vote vote1 = new Vote(lunchEventID, restaurantAdaptor.getItem(0), 1);
                Vote vote2 = new Vote(lunchEventID, restaurantAdaptor.getItem(1), 2);
                Vote vote3 = new Vote(lunchEventID, restaurantAdaptor.getItem(2), 3);
//                System.out.println(newVote);
                ParsePutter parsePutter = new ParsePutter(thisActivity);
                parsePutter.saveVote(vote1);
                parsePutter.saveVote(vote2);
                parsePutter.saveVote(vote3);

                Intent postVoteIntent = new Intent(RankingActivity.this, PostVoteActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putParcelable(PASS_TO_POST_VOTE_ACTIVITY_KEY, chosenLunchEvent);
                postVoteIntent.putExtras(mBundle);

                startActivity(postVoteIntent);
            }
        });

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Vote");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.whereEqualTo("relatedLunch", ParseObject.createWithoutData("LunchEvent", lunchEventID));
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (parseObjects.size() == 0) {
                    System.out.println("*******You have not voted yet.  Please go ahead.  It's your duty!");
                } else {
                    System.out.println("************You've already voted!");
                    Intent postVoteIntent = new Intent(RankingActivity.this, PostVoteActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putParcelable(PASS_TO_POST_VOTE_ACTIVITY_KEY, chosenLunchEvent);
                    postVoteIntent.putExtras(mBundle);

                    startActivity(postVoteIntent);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ranking, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void voteResultsAreReady(Multimap<String, String> voteResultsReadyMultimap) {
        voteResultsMultimap = voteResultsReadyMultimap;
    }
}
