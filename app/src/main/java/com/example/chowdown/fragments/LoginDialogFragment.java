package com.example.chowdown.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.chowdown.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginDialogFragment extends DialogFragment {


    public LoginDialogFragment() {
    }

    public EditText mUsernameEditText;
    public EditText mPasswordEditText;
    public Button mSaveButton;
    public static final String USERNAME_KEY = "USERNAME_KEY";
    public static final String PASSWORD_KEY = "PASSWORD_KEY";
    public static final String LOG_TAG = "LoginDialogFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.dialog_fragment_login, container, false);

        mUsernameEditText = (EditText) root.findViewById(R.id.editText_username_entry);
        mPasswordEditText = (EditText) root.findViewById(R.id.editText_password_entry);

        mSaveButton = (Button) root.findViewById(R.id.button_save);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mUsernameEditText.getText().toString().trim().length() != 0 & mPasswordEditText.getText().toString().trim().length() != 0){

                    final String username = (mUsernameEditText.getText().toString());
                    Log.d(LOG_TAG, "THE USERNAME IS: " + username);

                    final String password = (mPasswordEditText.getText().toString());
                    Log.d(LOG_TAG, "THE PASSWORD IS: " + password);

                    //saves the "saved" username in the editText to SharedPreferences
                    PreferenceManager.getDefaultSharedPreferences(getActivity())
                            .edit()
                            .putString(USERNAME_KEY, username)
                            .commit();

                    PreferenceManager.getDefaultSharedPreferences(getActivity())
                            .edit()
                            .putString(PASSWORD_KEY, password)
                            .commit();

                    ParseUser user = new ParseUser();
                    user.setUsername(username);
                    user.setPassword(password);

                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                //
                            } else {
//                                Context context = getActivity();
//                                CharSequence text = "Something was wrong! Try again!";
//                                int duration = Toast.LENGTH_SHORT;
//
//                                Toast toast = Toast.makeText(context, text, duration);
//                                toast.show();
                            }
                        }
                    });

                    dismiss();
                }

            }

        });

        // Inflate the layout for this fragment
        return root;
    }
}
