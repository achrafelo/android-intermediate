package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by possiblelabs on 7/18/15.
 */
public class LoginActivity extends Activity {

    private static final String TAG = "LA";


    private EditText username;
    private EditText password;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.login);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        if (!ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
            ParseUser currentUser = ParseUser.getCurrentUser();
            if (currentUser != null) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }


        username = (EditText) findViewById(R.id.txtUser);
        password = (EditText) findViewById(R.id.txtPass);
    }

    public void doLogin(View view) {

        final String usernametxt = username.getText().toString();
        final String passwordtxt = password.getText().toString();

        ParseUser.logInInBackground(usernametxt, passwordtxt,
                new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {

                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();

                        } else {

                            Toast.makeText(getApplicationContext(), "This user doesn't exist. Please signup", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void doSignup(View view) {

        final String usernametxt = username.getText().toString();
        final String passwordtxt = password.getText().toString();


        if (usernametxt.isEmpty() && passwordtxt.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please complete the sign up form", Toast.LENGTH_SHORT).show();
        } else {

            ParseUser user = new ParseUser();
            user.setUsername(usernametxt);
            user.setPassword(passwordtxt);
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(getApplicationContext(), "Successfully Signed up!", Toast.LENGTH_SHORT).show();

                    } else {
                        e.printStackTrace();
                        Log.d(TAG, e.toString());
                        Toast.makeText(getApplicationContext(), "Sign up error", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }
}
