package com.example.chowdown.activities;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.chowdown.adapters.LunchEventAdapter;
import com.example.chowdown.models.ParseConverterObject;
import com.example.chowdown.network.LunchEventParseGrabber;
import com.example.chowdown.R;

import com.parse.ParseObject;

import com.example.chowdown.fragments.LoginDialogFragment;
import com.example.chowdown.models.LunchEvent;
import com.example.chowdown.network.LunchEventParseGrabber;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    public static final String USERNAME_KEY = "USERNAME_KEY";
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

        int i = 0;
        for (ParseObject pO: pOL) {
            arrayOfLunches.add(i, mParseConverterObject.parseToObject(pO));
            i++;
        }

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
