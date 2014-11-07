package com.example.chowdown.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.chowdown.R;
import com.example.chowdown.models.LunchEvent;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

/**
 * Created by Borham on 11/6/14.
 */
public class LunchDetailFragment extends Fragment {
    public TextView titleText;
    public TextView dateText;
    public TextView timeFrameText;
    public TextView lunchDescriptionText;
    public TextView startedByText;
    public TextView attendeesText;
    public TextView votingStatusText;
    public TextView votingDetailsText;
    public static final String CHOSEN_LUNCH_KEY = "CHOSEN_LUNCH_KEY";

    public Button noButton;
    public Button yesButton;

    public static LunchDetailFragment newInstance(LunchEvent event){
        Bundle args = new Bundle();
        args.putParcelable(CHOSEN_LUNCH_KEY, event);
        LunchDetailFragment fragment =  new LunchDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public LunchDetailFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lunch_detail, container, false);

        Bundle data = getArguments();
        LunchEvent chosenLunchEvent = (LunchEvent) data.getParcelable(CHOSEN_LUNCH_KEY);

        titleText = (TextView)root.findViewById(R.id.lunch_detail_title);
        titleText.setText(R.string.lunch_detail_title_text);

        dateText = (TextView)root.findViewById(R.id.text_detail_date);
        dateText.setText(chosenLunchEvent.getStartDate().getMonthOfYear() + "/" + chosenLunchEvent.getStartDate().getDayOfMonth());

        timeFrameText = (TextView)root.findViewById(R.id.text_detail_time_frame);
        timeFrameText.setText(chosenLunchEvent.getStartDate().getHourOfDay() + ":" + chosenLunchEvent.getStartDate().getMinuteOfHour() + " - " + chosenLunchEvent.getEndDate().getHourOfDay() + ":" + chosenLunchEvent.getEndDate().getMinuteOfHour());

        lunchDescriptionText = (TextView)root.findViewById(R.id.text_detail_lunch_description);
        String description = chosenLunchEvent.description;
        if (description == null){
            description = "(No Description)";
        }
        lunchDescriptionText.setText("Description:\n" + description);

        startedByText = (TextView)root.findViewById(R.id.text_started_by);
        startedByText.setText("Event created by:\n" + chosenLunchEvent.getEventStarter());

        attendeesText = (TextView)root.findViewById(R.id.text_detail_attendees);
        attendeesText.setText(getStringOfAttendees(chosenLunchEvent));

        votingStatusText = (TextView)root.findViewById(R.id.voting_status);
        votingStatusText.setText(getStringThatShowsWhenVotingEnds(chosenLunchEvent));

        votingDetailsText = (TextView)root.findViewById(R.id.voting_details);
        votingDetailsText.setText(R.string.voting_details_text);

        noButton = (Button)root.findViewById(R.id.no_button);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getFragmentManager().popBackStack();
            }
        });

        yesButton = (Button)root.findViewById(R.id.yes_button);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //sends user to voting activity

            }
        });

        return root;
    }

    private String getStringThatShowsWhenVotingEnds(LunchEvent chosenLunchEvent) {
        DateTime currentDateTime = DateTime.now();
        Interval votingInterval = new Interval(currentDateTime, chosenLunchEvent.getVotingDate());
        Period timeLeftUntilVotingEnds = votingInterval.toPeriod();

        String stringThatShowsWhenVotingEnds = "Time until voting ends:\n";
        int yearsUntilVotingEnds = timeLeftUntilVotingEnds.getYears();
        int monthsUntilVotingEnds = timeLeftUntilVotingEnds.getMonths();
        int daysUntilVotingEnds = timeLeftUntilVotingEnds.getDays();
        int hoursUntilVotingEnds = timeLeftUntilVotingEnds.getHours();
        int minutesLeftUntilVotingEnds = timeLeftUntilVotingEnds.getMinutes();

        if (yearsUntilVotingEnds > 0) {
            stringThatShowsWhenVotingEnds = stringThatShowsWhenVotingEnds + yearsUntilVotingEnds + " Years ";
        }

        if (monthsUntilVotingEnds > 0) {
            stringThatShowsWhenVotingEnds = stringThatShowsWhenVotingEnds + monthsUntilVotingEnds + " Months ";
        }

        if (daysUntilVotingEnds > 0) {
            stringThatShowsWhenVotingEnds = stringThatShowsWhenVotingEnds + daysUntilVotingEnds + " Days ";
        }

        if (hoursUntilVotingEnds > 0) {
            stringThatShowsWhenVotingEnds = stringThatShowsWhenVotingEnds + minutesLeftUntilVotingEnds + " Minutes";
        }

        return stringThatShowsWhenVotingEnds;
    }

    private String getStringOfAttendees(LunchEvent chosenLunchEvent) {
        String allTheAttendees = "Attending:\n";
        for (int i = 0; i < chosenLunchEvent.getEventAttendees().size(); i++){
            if (i < (chosenLunchEvent.getEventAttendees().size() - 1)){
                allTheAttendees = allTheAttendees + chosenLunchEvent.getEventAttendees().get(i) + ", ";
            }
            else if (i < (chosenLunchEvent.getEventAttendees().size() - 2)) {
                allTheAttendees = allTheAttendees + chosenLunchEvent.getEventAttendees().get(i) + " ";
            }
            else{
                allTheAttendees = allTheAttendees + "and " + chosenLunchEvent.getEventAttendees().get(i);
            }
        }
        return allTheAttendees;
    }
}