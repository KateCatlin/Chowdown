package com.example.chowdown.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chowdown.R;
import com.example.chowdown.fragments.LunchDetailFragment;
import com.example.chowdown.models.LunchEvent;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Borham on 11/10/14.
 */
public class LunchDetailActivity extends Activity{
    public static final String CHOSEN_LUNCH_KEY = "CHOSEN_LUNCH_KEY";
    private LunchEvent chosenLunch;
    public TextView votingStatus;
    public CountDownTimer voteCountdownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_lunch_detail);

        chosenLunch = getIntent().getParcelableExtra(CHOSEN_LUNCH_KEY);

        FragmentManager fm = getFragmentManager();
        Fragment detailFragment = fm.findFragmentById(R.id.lunch_detail_container);

        if (detailFragment == null) {
            detailFragment = LunchDetailFragment.newInstance(chosenLunch);
            fm.beginTransaction()
                    .add(R.id.lunch_detail_container, detailFragment)
                    .commit();
        }

        Parse.initialize(this, "hQ5iOAVCIZ4BCepP1zco5r1HcoTp0uuvQUhLgUyX", "Hi4IYWhFI3L7EJLaX5KIRTTJvlt6DvBQHSDSTKgS");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Vote");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.whereEqualTo("relatedLunch", ParseObject.createWithoutData("LunchEvent", chosenLunch.getEventID()));
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (parseObjects.size() == 0) {
                    System.out.println("*******You have not voted yet.  Please go ahead.  It's your duty!");
                    makeVoteButtonsVisible();
                    makeVoteStatusVisible();
                } else {
                    System.out.println("************You've already voted!");
                    updateVoteStatus("Thank you for voting.  See you there!");
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void makeVoteButtonsVisible() {
        LinearLayout buttonWrapper = (LinearLayout)findViewById(R.id.vote_button_container);
        buttonWrapper.setVisibility(View.VISIBLE);
    }

    void makeVoteStatusVisible() {
        TextView votingStatusTextView = (TextView)findViewById(R.id.voting_details);
        votingStatusTextView.setVisibility(View.VISIBLE);
    }

    void updateVoteStatus(String newVotingStatus) {
        TextView votingStatusTextView = (TextView)findViewById(R.id.voting_details);
        votingStatusTextView.setText(newVotingStatus);
    }
}
