package com.example.chowdown.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chowdown.R;
import com.example.chowdown.activities.MainActivity;
import com.example.chowdown.models.LunchEvent;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

import com.example.chowdown.activities.MainActivity;


public class LunchEventAdapter extends ArrayAdapter<LunchEvent> {
    private final Context mContext;

    DateTime dt = new DateTime();
    int month = dt.getMonthOfYear();


    ArrayList<LunchEvent> lunchEventArrayList;


    public LunchEventAdapter(Context context, ArrayList<LunchEvent> lunchEventArrayList) {
        super(context, R.layout.row_lunch, lunchEventArrayList);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View thisRow = inflater.inflate(R.layout.row_lunch, parent, false);

        LunchEvent lunchObject = getItem(position);

        lunchEventArrayList = MainActivity.arrayLunchEvents;

//        this.eventID = eventID;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.votingDate = votingDate;
//        this.eventAttendees = eventAttendees;
//        this.topRestaurant = topRestaurant;

        TextView date = (TextView) thisRow.findViewById(R.id.text_date);
//        date.setText(lunchObject.getStartDate());

        TextView time = (TextView) thisRow.findViewById(R.id.text_time_frame);
        date.setText("!!!!!!!!!!!!");

        TextView lunchIndentifier = (TextView) thisRow.findViewById(R.id.text_lunch_identifier);
        lunchIndentifier.setText("!!!!!!!!!!!!");

        TextView lunchStarter = (TextView) thisRow.findViewById(R.id.text_started_by);
        lunchIndentifier.setText("!!!!!!!!!!!!");

        TextView attendees = (TextView) thisRow.findViewById(R.id.text_attending);
        lunchIndentifier.setText("!!!!!!!!!!!!");

        TextView text_started_by = (TextView) thisRow.findViewById(R.id.voting_status);
        lunchIndentifier.setText("!!!!!!!!!!!!");


        return thisRow;

    }

}


//}
