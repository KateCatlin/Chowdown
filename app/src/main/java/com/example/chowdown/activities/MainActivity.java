package com.example.chowdown.activities;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.chowdown.R;
import com.example.chowdown.fragments.LoginDialogFragment;
import com.example.chowdown.fragments.MainFragment;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends Activity  {

    public static final String USERNAME_KEY = "USERNAME_KEY";
    private static String APPLICATION_ID = "hQ5iOAVCIZ4BCepP1zco5r1HcoTp0uuvQUhLgUyX";
    private static String CLIENT_KEY = "Hi4IYWhFI3L7EJLaX5KIRTTJvlt6DvBQHSDSTKgS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);

        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        Fragment mainFrag = fm.findFragmentById(R.id.container);

        if (mainFrag ==null) {
            mainFrag = new MainFragment();
            fm.beginTransaction()
                    .add(R.id.container, mainFrag)
                    .commit();
        }

        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
            Log.d("LOG_TAG", "made it inot currentUser ==null");
            DialogFragment loginDialog = new LoginDialogFragment();
            loginDialog.setCancelable(false);
            loginDialog.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
            loginDialog.show(getFragmentManager(), "LoginDialogFragment");
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
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
