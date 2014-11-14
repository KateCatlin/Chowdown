package com.example.chowdown.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.chowdown.R;
import com.example.chowdown.controllers.GetChosenDateInterface;

import org.joda.time.DateTime;

/**
 * Created by Borham on 11/12/14.
 */
public class DateTimeDialogFragment extends DialogFragment {
    public static final String CHOSEN_DATE_KEY = "CHOSEN_DATE_KEY";
    public static final String WHICH_DATE_KEY = "WHICH_DATE_KEY";
    public DatePicker datePicker;
    public TimePicker timePicker;
    public Button setDateTime;
    public DateTime chosenDate;
    public String whichDateString;
    public GetChosenDateInterface getChosenDateInterface;

    public DateTimeDialogFragment(){
    }

    public static DateTimeDialogFragment newInstance(DateTime chosenDate, String whichDateString){
        Bundle args = new Bundle();
        args.putSerializable(CHOSEN_DATE_KEY, chosenDate);
        args.putString(WHICH_DATE_KEY, whichDateString);
        DateTimeDialogFragment fragment =  new DateTimeDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getChosenDateInterface = (GetChosenDateInterface) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling fragment must implement DialogClickListener interface");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog_fragment_date_time, container, false);
        chosenDate = (DateTime) getArguments().getSerializable(CHOSEN_DATE_KEY);
        whichDateString = getArguments().getString(WHICH_DATE_KEY);

        int year = chosenDate.getYear();
        int month = chosenDate.getMonthOfYear();
        int day = chosenDate.getDayOfMonth();
        int hour = chosenDate.getHourOfDay();
        int minutes = chosenDate.getMinuteOfHour();

        datePicker = (DatePicker) root.findViewById(R.id.datePicker);
        datePicker.init(year, --month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                month++;

                chosenDate = chosenDate.withYear(year);
                chosenDate = chosenDate.withMonthOfYear(month);

                if (day != 0){
                    switch (month){
                        case 2:
                            if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)){
                                if (day > 0 && day < 30) {
                                    chosenDate = chosenDate.withDayOfMonth(day);
                                }
                            }

                            else if (day > 0 && day < 29) {
                                chosenDate = chosenDate.withDayOfMonth(day);
                            }

                            break;

                        case 4:
                        case 6:
                        case 9:
                        case 11:
                            if ((day > 0) && day < 31){
                                chosenDate = chosenDate.withDayOfMonth(day);
                            }

                            break;

                        default:
                            chosenDate = chosenDate.withDayOfMonth(day);
                            break;

                    }
                }
            }
        });

        timePicker = (TimePicker) root.findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minutes);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {

                chosenDate = chosenDate.withHourOfDay(hour);
                chosenDate = chosenDate.withMinuteOfHour(minute);

            }
        });

        setDateTime = (Button) root.findViewById(R.id.button_set_time_date);
        setDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getChosenDateInterface.getChosenDate(chosenDate, whichDateString);
                dismiss();
            }
        });

        return root;
    }
}
