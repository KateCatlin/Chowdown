package com.example.chowdown.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.chowdown.R;

import org.joda.time.DateTime;

/**
 * Created by Borham on 11/12/14.
 */
public class DateTimeDialogFragment extends DialogFragment {

    public DatePicker datePicker;
    public TimePicker timePicker;
    public Button setDateTime;
    public DateTime chosenDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog_fragment_date_time, container, false);
        chosenDate = DateTime.now();

        int year = chosenDate.getYear();
        int month = chosenDate.getMonthOfYear();
        int day = chosenDate.getDayOfMonth();
        int hour = chosenDate.getHourOfDay();
        int minutes = chosenDate.getMinuteOfHour();

        datePicker = (DatePicker) root.findViewById(R.id.datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                Log.d("DateTimeDialog","*******INSIDE THE ONDATECHANGED*******");


                chosenDate.withYear(year);
                if (month != 0)chosenDate.withMonthOfYear(month);
                if (day != 0){
                    switch (month){
                        case 2:
                            if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)){
                                if (day > 0 && day < 30) {
                                    chosenDate.withDayOfMonth(day);
                                }
                            }

                            else if (day > 0 && day < 29) {
                                chosenDate.withDayOfMonth(day);
                            }

                            break;

                        case 4:
                        case 6:
                        case 9:
                        case 11:
                            if ((day > 0) && day < 31){
                                chosenDate.withDayOfMonth(day);
                            }

                            break;

                        default:
                            chosenDate.withDayOfMonth(day);
                            break;

                    }
                }
            }
        });

        timePicker = (TimePicker) root.findViewById(R.id.timePicker);
        timePicker.setCurrentHour(chosenDate.getHourOfDay());
        timePicker.setCurrentMinute(chosenDate.getMinuteOfDay());
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {

                chosenDate.withHourOfDay(hour);
                chosenDate.withMinuteOfHour(minute);

            }
        });

        setDateTime = (Button) root.findViewById(R.id.button_set_time_date);
        setDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), getString(R.string.datetime_are_set), Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}
