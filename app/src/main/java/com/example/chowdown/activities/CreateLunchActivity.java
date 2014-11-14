package com.example.chowdown.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.example.chowdown.R;
import com.example.chowdown.fragments.CreateLunchFragment;

/**
 * Created by Borham on 11/11/14.
 */
public class CreateLunchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_create_lunch);

        FragmentManager fm = getFragmentManager();
        Fragment createFrag = fm.findFragmentById(R.id.create_lunch_container);

        if (createFrag ==null) {
            createFrag = new CreateLunchFragment();
            fm.beginTransaction()
                    .add(R.id.create_lunch_container, createFrag)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
