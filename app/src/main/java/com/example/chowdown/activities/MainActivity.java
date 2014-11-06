package com.example.chowdown.activities;

import android.app.Activity;
import android.content.Context;
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
import com.example.chowdown.models.ParseConverterObject;
import com.example.chowdown.network.LunchEventParseGrabber;
import com.parse.ParseObject;

import java.util.List;
import com.example.chowdown.models.LunchEvent;
import com.parse.Parse;
import com.parse.ParseObject;

import org.joda.time.DateTime;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class MainActivity extends Activity {
    LunchEventAdapter mLunchEventAdapter;
    LunchEventParseGrabber lunchEventParseGrabber;
    ParseConverterObject mParseConverterObject = new ParseConverterObject();
    public static ArrayList<LunchEvent> arrayOfLunches = new ArrayList<LunchEvent>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listview);

        lunchEventParseGrabber = new LunchEventParseGrabber(this);

        lunchEventParseGrabber.testPostToParse();

        List<ParseObject> pOL = lunchEventParseGrabber.getLunchEvents();

//        mParseConverterObject = new ParseConverterObject();

        int i = 0;
        for (ParseObject pO: pOL) {
//            arrayOfLunches.add(mParseConverterObject.parseToObject(pO));
            arrayOfLunches.add(i, mParseConverterObject.parseToObject(pO));
//            Log.d("Log_Cat", "arrayOfLunches in Main is " + arrayOfLunches.get(i).getDescription());
//            System.out.println(pO.getString("topRestaurant"));
            i++;
        }

        Log.d("Log_Cat", "arrayOfLunches in Main is " + arrayOfLunches.get(0).getStartDate());
        Log.d("Log_Cat", "arrayOfLunches in Main is " + arrayOfLunches.get(1).getStartDate());

//        String dummyID1 = "1";
//        String dummyID2 = "2";
//        String dummyID3 = "3";
//        DateTime dummyDate = new DateTime(2014, 11, 5, 4, 50, 30);
//        ArrayList<String> eventAttendeesStringArray = new ArrayList<String>();
//        eventAttendeesStringArray.add("Cory");
//        eventAttendeesStringArray.add("Kate");
//        eventAttendeesStringArray.add("Ken");
//        eventAttendeesStringArray.add("Matt");
//
//        String topRestaurant1 = "Steve's Deli";
//        String topRestaurant2 = "Al's";
//        String topRestaurant3 = "7Greens";
//
//        LunchEvent lunch1 = new LunchEvent(dummyID1, "Cory's Lunch", dummyDate, dummyDate, dummyDate, eventAttendeesStringArray, topRestaurant1);
//        LunchEvent lunch2 = new LunchEvent(dummyID2, "Kate's Lunch", dummyDate, dummyDate, dummyDate, eventAttendeesStringArray, topRestaurant2);
//        LunchEvent lunch3 = new LunchEvent(dummyID3, "Ken's Lunch", dummyDate, dummyDate, dummyDate, eventAttendeesStringArray, topRestaurant3);
//        LunchEvent lunch4 = new LunchEvent(dummyID3, "Matt's Lunch", dummyDate, dummyDate, dummyDate, eventAttendeesStringArray, topRestaurant3);
//
//        arrayOfLunches.add(lunch1);
//        arrayOfLunches.add(lunch2);
//        arrayOfLunches.add(lunch3);
//        arrayOfLunches.add(lunch4);

        mLunchEventAdapter = new LunchEventAdapter(this, arrayOfLunches);

        mLunchEventAdapter.addAll(arrayOfLunches);

        listView.setAdapter(mLunchEventAdapter);

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