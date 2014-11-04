package com.example.chowdown.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.chowdown.R;

public class LoginFragment extends Fragment {

    EditText mUsernameEditText;
    Button mSaveButton;
    public static final String USERNAME_KEY = "USERNAME_KEY";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mUsernameEditText = (EditText) getActivity().findViewById(R.id.editText_username_entry);
        mSaveButton = (Button) getActivity().findViewById(R.id.button_save);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = mUsernameEditText.getText().toString();

                //saves the "saved" username in the editText to SharedPreferences
                PreferenceManager.getDefaultSharedPreferences(getActivity())
                        .edit()
                        .putString(USERNAME_KEY, username)
                        .commit();

                //jumps back to main activity???
                getActivity().getFragmentManager().popBackStack();
            }
        });

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}
