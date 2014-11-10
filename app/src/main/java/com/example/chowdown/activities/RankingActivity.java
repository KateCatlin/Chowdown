package com.example.chowdown.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.chowdown.R;
import com.example.chowdown.adapters.StableArrayAdapter;
import com.example.chowdown.views.DynamicListView;

import java.util.ArrayList;

public class RankingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        DynamicListView topRestaurantsListView = (DynamicListView) findViewById(R.id.ranked_restaurants_listview);

        ArrayList<String> restaurants = new ArrayList<String>();
        restaurants.add("Slices");
        restaurants.add("Orchid Thai");
        restaurants.add("TAQO");
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
}
