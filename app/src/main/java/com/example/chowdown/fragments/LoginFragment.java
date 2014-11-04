package com.example.chowdown.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.chowdown.R;

public class LoginFragment extends Fragment {

    EditText mUsernameEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mUsernameEditText = (EditText) findViewById();
        String username = mUsernameEditText.getText().toString();


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}
