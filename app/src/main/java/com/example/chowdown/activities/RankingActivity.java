package com.example.chowdown.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chowdown.R;
import com.example.chowdown.adapters.StableArrayAdapter;
import com.example.chowdown.views.DynamicListView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

import java.util.ArrayList;
import java.util.List;

public class RankingActivity extends Activity {

    public static final String CHOSEN_LUNCH_EVENT_ID = "CHOSEN_LUNCH_EVENT_ID";
    List<ParseObject> pOL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        Intent intent = getIntent();
        String lunchEventID = intent.getStringExtra(CHOSEN_LUNCH_EVENT_ID);
        TextView testTextView1 = (TextView) findViewById(R.id.test_text_view1);
        TextView testTextView2 = (TextView) findViewById(R.id.test_text_view2);
        testTextView1.setText(lunchEventID);

        ParseObject submitTestVote = new ParseObject("Vote");
        submitTestVote.put("userID", "FakeUser");
        submitTestVote.put("vote1", "Restaurant1");
        submitTestVote.put("vote2", "Restaurant2");
        submitTestVote.put("vote3", "Restaurant3");
        submitTestVote.put("voteForLunch", ParseObject.createWithoutData("LunchEvent", lunchEventID));

        submitTestVote.saveInBackground();

        ParseQuery<ParseObject> lunchQuery = new ParseQuery<ParseObject>("LunchEvent");
        lunchQuery.whereEqualTo("objectId", lunchEventID);
        ParseObject testLunchEvent = new ParseObject("lunchEvent");

        try {
             testLunchEvent = lunchQuery.find().get(0);
        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        // ParseQuery<ParseObject> voteQuery = new ParseQuery<ParseObject>("Vote");
        ParseRelation<ParseObject> relation = testLunchEvent.getRelation("voteForLunch");

        relation.getQuery().findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> results, ParseException e) {
                if (e != null) {
                    Log.d("findVotes", "The request failed.");
                } else {
                    Log.d("findVotes", "Found the votes.");
                    setQueryResults(results);
                    Log.d("results", pOL.toString());
                }
            }
        });

        //String topRestaurant = pOL.get(0).getString("vote1");
        //testTextView2.setText(topRestaurant);

        DynamicListView topRestaurantsListView = (DynamicListView) findViewById(R.id.ranked_restaurants_listview);

        ArrayList<String> restaurants = new ArrayList<String>();
        restaurants.add("Restaurant 1");
        restaurants.add("Restaurant 2");
        restaurants.add("Restaurant 3");
        StableArrayAdapter adapter = new StableArrayAdapter(this, R.layout.list_item_restaurant, restaurants);

        topRestaurantsListView.setCheeseList(restaurants);
        topRestaurantsListView.setAdapter(adapter);
        topRestaurantsListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
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

    public void setQueryResults(List<ParseObject> results) {
        pOL = results;
    }
}
