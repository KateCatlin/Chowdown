package com.example.chowdown.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.chowdown.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ListActivity {
    private List<Note> posts;

    String APPLICATION_ID = "hQ5iOAVCIZ4BCepP1zco5r1HcoTp0uuvQUhLgUyX";
    String CLIENT_KEY = "Hi4IYWhFI3L7EJLaX5KIRTTJvlt6DvBQHSDSTKgS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);
        Parse.initialize(this, "h6Jfwyj8j8WH7jQUQUeVPQYh5ihIiyUMA3QlyCmW", "7c0iZ1z2gHtl4MjLNKLxpI8JduDFetA0EsgfxoPj");

        posts = new ArrayList<Note>();
        ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this, R.layout.list_item_layout, posts);
        setListAdapter(adapter);

        refreshPostList();
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

        switch (id) {

            case R.id.action_refresh: {
                refreshPostList();
                break;
            }

            case R.id.action_new: {
                Intent intent = new Intent(this, EditNoteActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_settings: {
                // Do something when user selects Settings from Action Bar overlay
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void refreshPostList() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        setProgressBarIndeterminateVisibility(true);

        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> postList, ParseException e) {
                setProgressBarIndeterminateVisibility(false);
                if (e == null) {
                    // If there are results, update the list of posts
                    // and notify the adapter
                    posts.clear();
                    for (ParseObject post : postList) {
                        Note note = new Note(post.getObjectId(), post.getString("title"), post.getString("content"));
                        posts.add(note);
                    }
                    ((ArrayAdapter<Note>) getListAdapter()).notifyDataSetChanged();
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }

            }
        });
    }
}
