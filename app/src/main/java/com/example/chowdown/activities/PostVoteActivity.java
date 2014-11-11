package com.example.chowdown.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chowdown.R;
import com.example.chowdown.adapters.StableArrayAdapter;
import com.example.chowdown.models.LunchEvent;
import com.example.chowdown.models.Vote;
import com.example.chowdown.network.ParsePutter;
import com.example.chowdown.views.DynamicListView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katecatlin on 11/11/14.
 */
public class PostVoteActivity extends Activity {
    public static final String PASS_TO_POST_VOTE_ACTIVITY_KEY = "PASS_TO_POST_VOTE_ACTIVITY_KEY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_vote);

        final LunchEvent chosenLunchEvent = (LunchEvent) getIntent().getParcelableExtra(PASS_TO_POST_VOTE_ACTIVITY_KEY);

        TextView title = (TextView) findViewById(R.id.post_vote_title);

        TextView time = (TextView) findViewById(R.id.post_vote_lunch_time);
        time.setText(chosenLunchEvent.getStartDate().toString("H:mm"));

        Button confirm = (Button) findViewById(R.id.confirm_button);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent postVoteIntent = new Intent(PostVoteActivity.this, MainActivity.class);
                startActivity(postVoteIntent);

            }
        });



    }


}
