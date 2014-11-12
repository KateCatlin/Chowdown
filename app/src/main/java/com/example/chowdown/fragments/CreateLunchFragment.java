package com.example.chowdown.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chowdown.R;

/**
 * Created by Borham on 11/11/14.
 */
public class CreateLunchFragment extends Fragment {
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_lunch, container, false);


        return rootView;
    }
}
