package com.example.chowdown.adapters;


import android.content.Context;
import android.util.Log;
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
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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

        lunchEventArrayList = MainActivity.arrayOfLunches;

        TextView date = (TextView) thisRow.findViewById(R.id.text_date);
        date.setText(lunchObject.getStartDate().getMonthOfYear() + "/" + lunchObject.getStartDate().getDayOfMonth());

        TextView time = (TextView) thisRow.findViewById(R.id.text_time_frame);
        time.setText(lunchObject.getStartDate().getHourOfDay() + ":" + lunchObject.getStartDate().getMinuteOfHour() + " - " + lunchObject.getEndDate().getHourOfDay() + ":" + lunchObject.getEndDate().getMinuteOfHour());


        TextView lunchDescription = (TextView) thisRow.findViewById(R.id.text_lunch_description);
        lunchDescription.setText(lunchObject.getDescription());
        Log.d("LunchEventAdapter", "description is " + lunchObject.getDescription());

        TextView lunchStarter = (TextView) thisRow.findViewById(R.id.text_started_by);
        lunchStarter.setText(lunchObject.getEventStarter());

        TextView attending = (TextView) thisRow.findViewById(R.id.text_attending);
        attending.setText( "Maybe Attending" );

        TextView votingStatus = (TextView) thisRow.findViewById(R.id.voting_status);
        votingStatus.setText( "No Vote");


        return thisRow;

    }

}


//}
