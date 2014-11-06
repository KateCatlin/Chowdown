package com.example.chowdown.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chowdown.R;
import com.example.chowdown.activities.MainActivity;
import com.example.chowdown.models.LunchEvent;

import org.joda.time.DateTime;

import java.util.ArrayList;


public class LunchEventAdapter extends ArrayAdapter<LunchEvent> {
    private final Context mContext;

    DateTime dt = new DateTime();
    int month = dt.getMonthOfYear();
    String LOG_CAT = "LunchEventAdapter";


    ArrayList<LunchEvent> lunchEventArrayList;


    public LunchEventAdapter(Context context, ArrayList<LunchEvent> lunchEventArrayList) {
        super(context, R.layout.row_lunch, lunchEventArrayList);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(LOG_CAT, "Made it into getView");
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View thisRow = inflater.inflate(R.layout.row_lunch, parent, false);

        LunchEvent lunchObject = getItem(position);

        lunchEventArrayList = MainActivity.arrayOfLunches;
        Log.d(LOG_CAT, "ArrayOfLunches is " + MainActivity.arrayOfLunches);
        Log.d(LOG_CAT, "The first object in the array is " + MainActivity.arrayOfLunches.get(0).toString());
        Log.d(LOG_CAT, "Array of lunches is " + lunchEventArrayList);

        TextView date = (TextView) thisRow.findViewById(R.id.text_date);
        Log.d(LOG_CAT, "lunch startDate is " + lunchObject.getStartDate());
        Log.d(LOG_CAT, "lunch startdate.getmonth is " + lunchObject.getStartDate().getMonthOfYear());
        date.setText(lunchObject.getStartDate().getMonthOfYear() + "/" + lunchObject.getStartDate().getDayOfMonth());

        TextView time = (TextView) thisRow.findViewById(R.id.text_time_frame);
        time.setText(lunchObject.getStartDate().getHourOfDay() + ":" + lunchObject.getStartDate().getMinuteOfHour() + " - " + lunchObject.getEndDate().getHourOfDay() + ":" + lunchObject.getEndDate().getMinuteOfHour());


        TextView lunchDescription = (TextView) thisRow.findViewById(R.id.text_lunch_description);
        lunchDescription.setText(lunchObject.getDescription());
        Log.d("LunchEventAdapter", "description is " + lunchObject.getDescription());

        TextView lunchStarter = (TextView) thisRow.findViewById(R.id.text_started_by);
        lunchStarter.setText("Started by " + lunchObject.getEventStarter());

        TextView attending = (TextView) thisRow.findViewById(R.id.text_attending);
        attending.setText( "Maybe Attending" );

        TextView votingStatus = (TextView) thisRow.findViewById(R.id.voting_status);
        votingStatus.setText( "No Vote");


        return thisRow;

    }

}


//}
