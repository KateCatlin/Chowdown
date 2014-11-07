package com.example.chowdown.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.chowdown.R;
import com.example.chowdown.adapters.LunchEventAdapter;
import com.example.chowdown.fragments.LunchDetailFragment;
import com.example.chowdown.models.LunchEvent;
import com.example.chowdown.models.ParseConverterObject;
import com.example.chowdown.network.LunchEventParseGrabber;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    public static final String USERNAME_KEY = "USERNAME_KEY";
    LunchEventAdapter mLunchEventAdapter;
    LunchEventParseGrabber lunchEventParseGrabber;
    ParseConverterObject mParseConverterObject = new ParseConverterObject();
    public static ArrayList<LunchEvent> arrayOfLunches = new ArrayList<LunchEvent>();
    public static final String CHOSEN_LUNCH_KEY = "CHOSEN_LUNCH_KEY";
    public static final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listview);

        lunchEventParseGrabber = new LunchEventParseGrabber(this);

        lunchEventParseGrabber.testPostToParse();

        List<ParseObject> pOL = lunchEventParseGrabber.getLunchEvents();

        int i = 0;
        for (ParseObject pO: pOL) {
            arrayOfLunches.add(i, mParseConverterObject.parseToObject(pO));
            i++;
        }

        mLunchEventAdapter = new LunchEventAdapter(this, arrayOfLunches);

        mLunchEventAdapter.addAll(arrayOfLunches);

        listView.setAdapter(mLunchEventAdapter);

        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Log.d(LOG_TAG, "ITEM CLICKED IN ADAPTER VIEW");
        LunchEvent chosenLunch = mLunchEventAdapter.getItem(position);

//        Parcel lunchBox;
//        chosenLunch.writeToParcel(lunchBox, );
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        LunchDetailFragment lunchDetailFragment = LunchDetailFragment.newInstance(chosenLunch);
        ft.add(R.id.detail_container,lunchDetailFragment).addToBackStack(null).commit();

//        Intent detailIntent = new Intent(this, LunchDetailFragment.class);
//        detailIntent.putExtra(CHOSEN_LUNCH_KEY, chosenLunch);
//        startActivity(detailIntent);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
