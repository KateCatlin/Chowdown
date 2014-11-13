package com.example.chowdown.fragments;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.chowdown.R;
import com.example.chowdown.activities.MainActivity;

/**
 * Created by Borham on 11/11/14.
 */
public class CreateLunchFragment extends Fragment {
    EditText editDescription;
    Button lunchStart;
    Button lunchEnd;
    Button voteEnd;
    Button createLunch;
    DialogFragment lunchStartDialogFragment;
    DialogFragment lunchEndDialogFragment;
    DialogFragment voteEndDialogFragment;


    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_lunch, container, false);
        lunchStartDialogFragment = new DateTimeDialogFragment();
        lunchEndDialogFragment = new DateTimeDialogFragment();
        voteEndDialogFragment = new DateTimeDialogFragment();

        editDescription = (EditText)rootView.findViewById(R.id.edit_description);

        lunchStart = (Button)rootView.findViewById(R.id.button_lunch_begins);
        lunchStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lunchStartDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
                lunchStartDialogFragment.show(getFragmentManager(), "LunchStartDialogFragment");
            }
        });

        lunchEnd = (Button)rootView.findViewById(R.id.button_lunch_ends);
        lunchEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lunchEndDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
                lunchEndDialogFragment.show(getFragmentManager(), "LunchEndDialogFragment");
            }
        });

        voteEnd = (Button)rootView.findViewById(R.id.button_vote_ends);
        voteEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voteEndDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
                voteEndDialogFragment.show(getFragmentManager(), "LunchStartDialogFragment");
            }
        });

        createLunch = (Button)rootView.findViewById(R.id.button_create_lunch);
        createLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                startActivity(mainIntent);
            }
        });

        return rootView;
    }
}
