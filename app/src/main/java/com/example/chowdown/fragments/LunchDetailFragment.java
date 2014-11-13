package com.example.chowdown.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.chowdown.R;
import com.example.chowdown.activities.MainActivity;
import com.example.chowdown.activities.RankingActivity;
import com.example.chowdown.models.LunchEvent;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

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
    public static final String PASS_TO_RANKING_KEY = "PASS_TO_RANKING_KEY";
    private CountDownTimer voteCountDownTimer;
    private LunchEvent chosenLunchEvent;

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

        chosenLunchEvent = (LunchEvent) data.getParcelable(CHOSEN_LUNCH_KEY);
        Log.d("LOG_TAG", "EventID is " + chosenLunchEvent.getEventID());

        titleText = (TextView)root.findViewById(R.id.lunch_detail_title);
        titleText.setText(R.string.lunch_detail_title_text);

        dateText = (TextView)root.findViewById(R.id.text_detail_date);
        dateText.setText(chosenLunchEvent.getStartDate().getMonthOfYear() + "/" + chosenLunchEvent.getStartDate().getDayOfMonth());

        timeFrameText = (TextView)root.findViewById(R.id.text_detail_time_frame);
        timeFrameText.setText(chosenLunchEvent.getStartDate().toString("H:mm") + " - " + chosenLunchEvent.getEndDate().toString("H:mm"));

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
                Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                startActivity(mainIntent);
            }
        });

        yesButton = (Button)root.findViewById(R.id.yes_button);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent rankingIntent = new Intent(getActivity(), RankingActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putParcelable(PASS_TO_RANKING_KEY, chosenLunchEvent);
                rankingIntent.putExtras(mBundle);

                startActivity(rankingIntent);
            }
        });

        DateTime currentDateTime = DateTime.now();
        Interval votingInterval = new Interval(currentDateTime, chosenLunchEvent.getVotingDate());
        int millisecondsLeft = (int)(votingInterval.getEndMillis() - votingInterval.getStartMillis());
        voteCountDownTimer = new CountDownTimer(millisecondsLeft, 1000) {
            public void onTick(long millisUntilFinished) {
                if (getActivity() != null) {
                    TextView votingStatus = (TextView) getActivity().findViewById(R.id.voting_status);
                    votingStatus.setText(getStringThatShowsWhenVotingEnds(chosenLunchEvent));
                }
            }

            public void onFinish() {
                TextView votingStatus = (TextView) getActivity().findViewById(R.id.voting_status);
                votingStatus.setText("Too late!");
            }
        };
        voteCountDownTimer.start();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        DateTime currentDateTime = DateTime.now();
        Interval votingInterval = new Interval(currentDateTime, chosenLunchEvent.getVotingDate());
        int millisecondsLeft = (int)(votingInterval.getEndMillis() - votingInterval.getStartMillis());
        voteCountDownTimer = new CountDownTimer(millisecondsLeft, 1000) {
            public void onTick(long millisUntilFinished) {
                if (getActivity() != null) {
                    TextView votingStatus = (TextView) getActivity().findViewById(R.id.voting_status);
                    votingStatus.setText(getStringThatShowsWhenVotingEnds(chosenLunchEvent));
                }
            }

            public void onFinish() {
                TextView votingStatus = (TextView) getActivity().findViewById(R.id.voting_status);
                votingStatus.setText("Voting over! You're eating at "+ chosenLunchEvent.getTopRestaurant());
            }
        };
        voteCountDownTimer.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        voteCountDownTimer.cancel();
        voteCountDownTimer = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        voteCountDownTimer = null;
    }

    private String getStringThatShowsWhenVotingEnds(LunchEvent chosenLunchEvent) {
        DateTime currentDateTime = DateTime.now();
        Interval votingInterval = new Interval(currentDateTime, chosenLunchEvent.getVotingDate());
        Period timeLeftUntilVotingEnds = votingInterval.toPeriod();
        PeriodFormatter timeLeftFormatter = new PeriodFormatterBuilder()
                .appendYears()
                .appendSuffix(" year", " years")
                .appendSeparator(", ")
                .appendMonths()
                .appendSuffix(" month", " months")
                .appendSeparator(", ")
                .appendDays()
                .printZeroAlways()
                .appendSuffix(" day", " days")
                .appendSeparator(", ")
                .appendHours()
                .appendSuffix(" hour", " hours")
                .appendSeparator(", and ")
                .appendMinutes()
                .appendSuffix(" minute", " minutes")
                .appendSeparator(", ")
                .appendSeconds()
                .appendSuffix(" second", " seconds")
                .toFormatter();



        String stringThatShowsWhenVotingEnds = "Time until voting ends:\n" + timeLeftFormatter.print(timeLeftUntilVotingEnds);

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
