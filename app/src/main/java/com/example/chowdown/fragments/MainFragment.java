package com.example.chowdown.fragments;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.chowdown.R;
import com.example.chowdown.activities.LunchDetailActivity;
import com.example.chowdown.activities.SettingsActivity;
import com.example.chowdown.adapters.LunchEventAdapter;
import com.example.chowdown.models.LunchEvent;
import com.example.chowdown.models.ParseConverterObject;
import com.example.chowdown.network.LunchEventParseGrabber;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment implements AdapterView.OnItemClickListener {


    public static final String USERNAME_KEY = "USERNAME_KEY";
    LunchEventAdapter mLunchEventAdapter;
    LunchEventParseGrabber lunchEventParseGrabber;
    ParseConverterObject mParseConverterObject = new ParseConverterObject();
    public static ArrayList<LunchEvent> arrayOfLunches = new ArrayList<LunchEvent>();
    public static final String CHOSEN_LUNCH_KEY = "CHOSEN_LUNCH_KEY";
    public static final String LOG_TAG = "MainActivity";

    public MainFragment () {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Log.d(LOG_TAG, "ITEM CLICKED IN ADAPTER VIEW");
        LunchEvent chosenLunch = mLunchEventAdapter.getItem(position);

        Intent detailIntent = new Intent(getActivity(), LunchDetailActivity.class);
        detailIntent.putExtra(CHOSEN_LUNCH_KEY,chosenLunch);
        startActivity(detailIntent);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview);

        lunchEventParseGrabber = new LunchEventParseGrabber(getActivity());

        lunchEventParseGrabber.testPostToParse();

        List<ParseObject> pOL = lunchEventParseGrabber.getLunchEvents();

        int i = 0;
        for (ParseObject pO: pOL) {
            arrayOfLunches.add(i, mParseConverterObject.parseToObject(pO));
            i++;

        }

        mLunchEventAdapter = new LunchEventAdapter(getActivity(), arrayOfLunches);

        mLunchEventAdapter.addAll(arrayOfLunches);

        listView.setAdapter(mLunchEventAdapter);

        listView.setOnItemClickListener(this);

        return rootView;
    }

}
