package com.example.chowdown.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.chowdown.R;
import com.example.chowdown.network.LunchEventParseGrabber;
import com.parse.ParseObject;

import java.util.List;
import com.example.chowdown.models.LunchEvent;
import com.parse.Parse;
import com.parse.ParseObject;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class MainActivity extends Activity {
    LunchEventParseGrabber lunchEventParseGrabber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lunchEventParseGrabber = new LunchEventParseGrabber(this);

        lunchEventParseGrabber.testPostToParse();

        List<ParseObject> pOL = lunchEventParseGrabber.getLunchEvents();
        for (ParseObject pO: pOL) {
            System.out.println(pO.getString("topRestaurant"));
        }

//        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        String dummyID1 = "1";
        String dummyID2 = "2";
        String dummyID3 = "3";
        Date dummyDate = new Date();
        String[] eventAttendeesStringArray = {"Cory", "Kate", "Ken", "Matt"};
        ArrayList<String> eventAttendees = new ArrayList<String>(Arrays.asList(eventAttendeesStringArray));
        String topRestaurant1 = "Steve's Deli";
        String topRestaurant2 = "Al's";
        String topRestaurant3 = "7Greens";

        LunchEvent lunch1 = new LunchEvent(dummyID1, dummyDate, dummyDate, dummyDate, eventAttendees, topRestaurant1);
        LunchEvent lunch2 = new LunchEvent(dummyID2, dummyDate, dummyDate, dummyDate, eventAttendees, topRestaurant2);
        LunchEvent lunch3 = new LunchEvent(dummyID3, dummyDate, dummyDate, dummyDate, eventAttendees, topRestaurant3);

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