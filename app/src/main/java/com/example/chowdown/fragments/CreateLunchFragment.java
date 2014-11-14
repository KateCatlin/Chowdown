package com.example.chowdown.fragments;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chowdown.R;
import com.example.chowdown.activities.MainActivity;
import com.example.chowdown.activities.RankingActivity;
import com.example.chowdown.controllers.GetChosenDateInterface;
import com.example.chowdown.models.LunchEvent;
import com.example.chowdown.models.ParseConverterObject;
import com.example.chowdown.network.LunchEventParseGrabber;
import com.parse.ParseObject;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Borham on 11/11/14.
 */
public class CreateLunchFragment extends Fragment implements GetChosenDateInterface {
    public static ArrayList<LunchEvent> arrayOfLunches = new ArrayList<LunchEvent>();
    public EditText editDescription;
    public Button lunchStartButton;
    public Button lunchEndButton;
    public Button voteEndButton;
    public Button createLunchButton;
    public DialogFragment lunchStartDialogFragment;
    public DialogFragment lunchEndDialogFragment;
    public DialogFragment voteEndDialogFragment;
    public LunchEvent newLunchEvent;
    public LunchEventParseGrabber mLunchEventParseGrabber;
    public final String VOTING_DATE_STRING = "VOTING_DATE_STRING";
    public final String START_DATE_STRING = "START_DATE_STRING";
    public final String END_DATE_STRING = "END_DATE_STRING";
    public boolean voteDateChanged;
    public boolean startDateChanged;
    public boolean endDateChanged;
    ParseConverterObject mParseConverterObject = new ParseConverterObject();
    public final String VOTE_CHANGE_KEY = "VOTE_CHANGE_KEY";
    public final String START_CHANGE_KEY = "START_CHANGE_KEY";
    public final String END_CHANGE_KEY = "END_CHANGE_KEY";
    public final String DESCRIPTION_KEY = "DESCRIPTION_KEY";
    public static final String PASS_TO_RANKING_KEY = "PASS_TO_RANKING_KEY";
    public static final String LOG_TAG = "LOG_TAG";


    String description;
    String objectID;
    DateTime startDate, endDate, votingDate;
    ArrayList<String> eventAttendees;
    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

    @Override
    public void getChosenDate(DateTime chosenDate, String whichDateString) {

        if(whichDateString.equals(VOTING_DATE_STRING)){
            votingDate = chosenDate;
            voteEndButton.setText(fmt.print(votingDate));
        }

        if(whichDateString.equals(START_DATE_STRING)){
            startDate = chosenDate;
            lunchStartButton.setText(fmt.print(startDate));
        }

        if(whichDateString.equals(END_DATE_STRING)){
            endDate = chosenDate;
            lunchEndButton.setText(fmt.print(endDate));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_lunch, container, false);

        if (savedInstanceState != null){
            voteDateChanged = savedInstanceState.getBoolean(VOTE_CHANGE_KEY);
            startDateChanged = savedInstanceState.getBoolean(START_CHANGE_KEY);
            endDateChanged = savedInstanceState.getBoolean(END_CHANGE_KEY);
            votingDate = (DateTime)savedInstanceState.getSerializable(VOTING_DATE_STRING);
            startDate = (DateTime)savedInstanceState.getSerializable(START_DATE_STRING);
            endDate = (DateTime)savedInstanceState.getSerializable(END_DATE_STRING);
            description = savedInstanceState.getString(DESCRIPTION_KEY);
        }

        else {
            votingDate = DateTime.now().withSecondOfMinute(0);
            startDate = DateTime.now().withSecondOfMinute(0);
            endDate = DateTime.now().withSecondOfMinute(0);
        }

        eventAttendees = new ArrayList<String>();
        eventAttendees.add(PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(MainActivity.USERNAME_KEY, "nobody"));
        eventAttendees.add("BO");

        editDescription = (EditText)rootView.findViewById(R.id.edit_description);
        if (description != null) {editDescription.setText(description);}
        editDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                description = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        voteEndDialogFragment = DateTimeDialogFragment.newInstance(votingDate, VOTING_DATE_STRING);
        voteEndDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        voteEndDialogFragment.setCancelable(false);
        voteEndDialogFragment.setTargetFragment(this, 0);

        voteEndButton = (Button)rootView.findViewById(R.id.button_vote_ends);

        if (voteDateChanged == true){voteEndButton.setText(fmt.print(votingDate));}

        voteEndButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voteEndDialogFragment.show(getFragmentManager(), "LunchStartDialogFragment");
                voteDateChanged = true;
            }
        });

        lunchStartDialogFragment = DateTimeDialogFragment.newInstance(startDate,START_DATE_STRING);
        lunchStartDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        lunchStartDialogFragment.setCancelable(false);
        lunchStartDialogFragment.setTargetFragment(this, 0);

        lunchStartButton = (Button)rootView.findViewById(R.id.button_lunch_begins);

        if (startDateChanged == true){lunchStartButton.setText(fmt.print(startDate));}

        lunchStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lunchStartDialogFragment.show(getFragmentManager(), "LunchStartDialogFragment");
                startDateChanged = true;
            }
        });

        lunchEndDialogFragment = DateTimeDialogFragment.newInstance(endDate, END_DATE_STRING);
        lunchEndDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        lunchEndDialogFragment.setCancelable(false);
        lunchEndDialogFragment.setTargetFragment(this, 0);

        lunchEndButton = (Button)rootView.findViewById(R.id.button_lunch_ends);

        if (endDateChanged == true){lunchEndButton.setText(fmt.print(endDate));}

        lunchEndButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lunchEndDialogFragment.show(getFragmentManager(), "LunchEndDialogFragment");
                endDateChanged = true;
            }
        });

        createLunchButton = (Button)rootView.findViewById(R.id.button_create_lunch);
        createLunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (voteDateChanged && startDateChanged && endDateChanged && (description != null)){

                    if (votingDate.isAfter(DateTime.now()) && startDate.isAfter(DateTime.now()) && endDate.isAfter(DateTime.now())){

                        if (votingDate.isBefore(startDate) && startDate.isBefore(endDate)){

                            LunchEventParseGrabber lunchEventParseGrabber = new LunchEventParseGrabber(getActivity());
                            newLunchEvent = new LunchEvent(null, description, startDate, endDate, votingDate, eventAttendees, null);
                            lunchEventParseGrabber.postToParse(newLunchEvent);

                            Toast.makeText(getActivity(), getString(R.string.lunch_created), Toast.LENGTH_SHORT).show();

                            mLunchEventParseGrabber = new LunchEventParseGrabber(getActivity());

                            List<ParseObject> pOL = lunchEventParseGrabber.getLunchEvents();

                            int i = 0;
                            for (ParseObject pO: pOL) {
                                arrayOfLunches.add(i, mParseConverterObject.parseObjectToLunchEvent(pO));
                                i++;
                            }

                            objectID = arrayOfLunches.get(0).getEventID();

                            newLunchEvent.setEventID(objectID);

                            Intent rankingIntent = new Intent(getActivity(), RankingActivity.class);
                            Bundle mBundle = new Bundle();
                            mBundle.putParcelable(PASS_TO_RANKING_KEY, newLunchEvent);
                            rankingIntent.putExtras(mBundle);

                            startActivity(rankingIntent);

                        }

                        else Toast.makeText(getActivity(), getString(R.string.date_order), Toast.LENGTH_LONG).show();
                    }

                    else Toast.makeText(getActivity(), getString(R.string.after_now), Toast.LENGTH_LONG).show();

                }

                else Toast.makeText(getActivity(),R.string.change_fields, Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(VOTE_CHANGE_KEY, voteDateChanged);
        outState.putBoolean(START_CHANGE_KEY, startDateChanged);
        outState.putBoolean(END_CHANGE_KEY, endDateChanged);
        outState.putSerializable(VOTING_DATE_STRING, votingDate);
        outState.putSerializable(START_DATE_STRING, startDate);
        outState.putSerializable(END_DATE_STRING, endDate);
        outState.putString(DESCRIPTION_KEY,description);
    }
}
