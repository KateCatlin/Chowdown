package com.example.chowdown.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.chowdown.R;
import com.example.chowdown.network.LunchEventParseGrabber;
import com.parse.ParseObject;

import java.util.List;

public class MainActivity extends Activity {
    LunchEventParseGrabber lunchEventParseGrabber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lunchEventParseGrabber = new LunchEventParseGrabber(this);
//        lunchEventParseGrabber.testPostToParse();
        List<ParseObject> pOL = lunchEventParseGrabber.getLunchEvents();
        for (ParseObject pO: pOL) {
            System.out.println(pO.getString("topRestaurant"));
        }
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
