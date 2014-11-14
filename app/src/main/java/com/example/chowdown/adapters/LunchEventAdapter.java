package com.example.chowdown.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chowdown.R;
import com.example.chowdown.fragments.MainFragment;
import com.example.chowdown.models.LunchEvent;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;


public class LunchEventAdapter extends ArrayAdapter<LunchEvent> {
    private final Context mContext;
    String LOG_CAT = "LunchEventAdapter";
    private static String APPLICATION_ID = "hQ5iOAVCIZ4BCepP1zco5r1HcoTp0uuvQUhLgUyX";
    private static String CLIENT_KEY = "Hi4IYWhFI3L7EJLaX5KIRTTJvlt6DvBQHSDSTKgS";

    DateTime dt = new DateTime();
    int month = dt.getMonthOfYear();

    ArrayList<LunchEvent> lunchEventArrayList;

    public LunchEventAdapter(Context context, ArrayList<LunchEvent> lunchEventArrayList) {
        super(context, R.layout.row_lunch, lunchEventArrayList);
        this.mContext = context;
    }

    public void clearAdapter()
    {
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Log.d(LOG_CAT, "Made it into View getView");

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View thisRow = inflater.inflate(R.layout.row_lunch, parent, false);

        LunchEvent lunchObject = getItem(position);

        lunchEventArrayList = MainFragment.arrayOfLunches;

        TextView date = (TextView) thisRow.findViewById(R.id.text_date);
        date.setText(lunchObject.getStartDate().getMonthOfYear() + "/" + lunchObject.getStartDate().getDayOfMonth());
        //Log.d(LOG_CAT, "date is " + date);

        TextView time = (TextView) thisRow.findViewById(R.id.text_time_frame);
        time.setText(lunchObject.getStartDate().toString("H:mm") + " - " + lunchObject.getEndDate().toString("H:mm"));

        TextView lunchDescription = (TextView) thisRow.findViewById(R.id.text_lunch_description);
        lunchDescription.setText(lunchObject.getDescription());

        TextView lunchStarter = (TextView) thisRow.findViewById(R.id.text_started_by);
        lunchStarter.setText("Started by " + lunchObject.getEventStarter());
        //Log.d(LOG_CAT, "started by is " + lunchStarter);

        final TextView attending = (TextView) thisRow.findViewById(R.id.text_attending);
        Parse.initialize(getContext(), APPLICATION_ID, CLIENT_KEY);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Vote");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.whereEqualTo("voteForLunch", ParseObject.createWithoutData("LunchEvent", lunchObject.getEventID()));
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (parseObjects.size() == 0) {
                    attending.setText("");
                } else {
                    attending.setText("You're SO going to this lunch!");
                }
            }
        });

        TextView votingStatus = (TextView) thisRow.findViewById(R.id.voting_status);
        votingStatus.setText( "No Vote");

        return thisRow;

    }

}

