package com.example.chowdown.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import com.example.chowdown.R;
import com.example.chowdown.fragments.LunchDetailFragment;
import com.example.chowdown.models.LunchEvent;

/**
 * Created by Borham on 11/10/14.
 */
public class LunchDetailActivity extends Activity{
    public static final String CHOSEN_LUNCH_KEY = "CHOSEN_LUNCH_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_detail);

        LunchEvent chosenLunch = getIntent().getParcelableExtra(CHOSEN_LUNCH_KEY);

        FragmentManager fm = getFragmentManager();
        Fragment detailFragment = fm.findFragmentById(R.id.lunch_detail_container);

        if (detailFragment == null) {
            detailFragment = LunchDetailFragment.newInstance(chosenLunch);
            fm.beginTransaction()
                    .add(R.id.lunch_detail_container, detailFragment)
                    .commit();
        }

    }
}
