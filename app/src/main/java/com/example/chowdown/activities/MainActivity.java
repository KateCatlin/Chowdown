package com.example.chowdown.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.chowdown.R;
import com.example.chowdown.models.LunchEvent;
import com.parse.Parse;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class MainActivity extends Activity {

    String APPLICATION_ID = "hQ5iOAVCIZ4BCepP1zco5r1HcoTp0uuvQUhLgUyX";
    String CLIENT_KEY = "Hi4IYWhFI3L7EJLaX5KIRTTJvlt6DvBQHSDSTKgS";
    public static final String USERNAME_KEY = "USERNAME_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);

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

        String username = PreferenceManager.getDefaultSharedPreferences(this).getString(USERNAME_KEY, null);

        if (username == null){
            final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();


        }

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
