package com.example.chowdown.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chowdown.R;
import com.example.chowdown.adapters.StableArrayAdapter;
import com.example.chowdown.network.VoteParseGrabber;
import com.example.chowdown.views.DynamicListView;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

public class RankingActivity extends Activity {


    public static final String CHOSEN_LUNCH_EVENT_ID = "CHOSEN_LUNCH_EVENT_ID";
    public static final String ORCHID_THAI_OBJECT_ID = "doLgBRhEzo";
    public static final String TAQO_OBJECT_ID = "YqLy4jHA2T";
    public static final String SLICE_OBJECT_ID = "A1ItP7AEuy";

    List<ParseObject> pOL;
    ParseObject testLunchEvent;

    StableArrayAdapter restaurantAdaptor;

    VoteParseGrabber voteParseGrabber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        Intent intent = getIntent();
        String lunchEventID = intent.getStringExtra(CHOSEN_LUNCH_EVENT_ID);
        TextView testTextView1 = (TextView) findViewById(R.id.title_text_view);
        TextView testTextView2 = (TextView) findViewById(R.id.title_text_view);
        testTextView1.setText(lunchEventID);

        voteParseGrabber = new VoteParseGrabber(this);

        voteParseGrabber.testPostToParse(lunchEventID);

        pOL = voteParseGrabber.getVotesByLunchID(lunchEventID);

        DynamicListView topRestaurantsListView = (DynamicListView) findViewById(R.id.ranked_restaurants_listview);

        ArrayList<String> restaurants = new ArrayList<String>();
        restaurants.add("Slices");
        restaurants.add("Orchid Thai");
        restaurants.add("TAQO");
        restaurantAdaptor = new StableArrayAdapter(this, R.layout.list_item_restaurant, restaurants);

        topRestaurantsListView.setCheeseList(restaurants);
        topRestaurantsListView.setAdapter(restaurantAdaptor);
        topRestaurantsListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        Button submitButton = (Button) findViewById(R.id.submit_vote_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("item 1 is" + restaurantAdaptor.getItem(0));
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

    public List<ParseObject> getQueryResults(List<ParseObject> results) {
        return results;
    }

    public void setTestLunchEvent(ParseObject object) {
        testLunchEvent = object;
    }
}
