package com.possiblelabs.firebasetest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class MainActivity extends Activity {

    private static final String TAG = "FIREBASETEST";
    private static final String FIREBASE_URL = "https://firebastest.firebaseio.com/ball";

    private RelativeLayout container;
    private DrawView dv;
    private Firebase ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        container = (RelativeLayout) findViewById(R.id.container);
        if (dv == null) {
            dv = new DrawView(this);
            container.addView(dv);
        }

        ref = new Firebase(FIREBASE_URL);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange");
                String position = (String) dataSnapshot.getValue();
                Log.d(TAG, "POSITION:" + position);
                String[] xy = position.split(",");
                if (xy.length != 2)
                    return;

                float x = Float.parseFloat(xy[0]);
                float y = Float.parseFloat(xy[1]);
                dv.setPosition(x, y);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void up(View v) {
        dv.up();
        ref.setValue(dv.pointToString());
    }

    public void down(View v) {
        dv.down();
        ref.setValue(dv.pointToString());
    }

    public void left(View v) {
        dv.left();
        ref.setValue(dv.pointToString());
    }

    public void right(View v) {
        dv.right();
        ref.setValue(dv.pointToString());
    }
}
