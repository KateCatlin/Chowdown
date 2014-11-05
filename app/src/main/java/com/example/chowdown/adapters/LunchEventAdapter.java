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
import com.example.chowdown.models.LunchEvent;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class LunchEventAdapter extends ArrayAdapter<LunchEvent> {
    private final Context mContext;
    java.util.Date date1 = new java.util.Date(2014, 11, 20, 12, 30);

    LunchEvent lunch1 = new LunchEvent("1", date1, date1, date1);
    LunchEvent lunch2 = new LunchEvent("2", date1, date1, date1);
    LunchEvent lunch3 = new LunchEvent("3", date1, date1, date1);
    LunchEvent lunch4 = new LunchEvent("4", date1, date1, date1);

    ArrayList<LunchEvent> lunchEventArrayList = new ArrayList<LunchEvent>();


    public LunchEventAdapter(Context context, ArrayList<LunchEvent> lunchEventArrayList) {
        super(context, R.layout.row_lunch, lunchEventArrayList);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View thisRow = inflater.inflate(R.layout.row_lunch, parent, false);
        LunchEvent lunchObject = getItem(position);

        TextView date = (TextView) thisRow.findViewById(R.id.text_date);
        date.setText("!!!!!!!!!!!!");

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
