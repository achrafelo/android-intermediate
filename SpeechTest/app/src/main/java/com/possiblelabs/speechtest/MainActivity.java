package com.possiblelabs.speechtest;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private EditText txtMesage;
    private ImageView mAnimatedGifView;
    private AnimationDrawable frameAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMesage = (EditText) findViewById(R.id.txtMessage);
        mAnimatedGifView = (ImageView) findViewById(R.id.animated_gif);

        loadImage();
    }

    private void loadImage() {

        frameAnimation = (AnimationDrawable) getResources().getDrawable(R.drawable.animation_list);
        frameAnimation.setOneShot(false);
        mAnimatedGifView.setBackgroundDrawable(frameAnimation);


        MainApplication.linkListener(new Talking() {
            @Override
            public void start(String start) {
                Log.d("start", start);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        frameAnimation.start();
                    }
                });

            }

            @Override
            public void stop(String stop) {
                Log.d("stop", stop);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        frameAnimation.stop();
                    }
                });

            }
        });

    }

    public void doTextToSpeech(View view) {
        String message = txtMesage.getText().toString();
        if (!message.isEmpty()) {
            MainApplication.speakOut(message);
        } else {
            Toast.makeText(this, "Empty Message", Toast.LENGTH_SHORT).show();
        }
    }

    public void doTextToSpell(View view) {
        String message = txtMesage.getText().toString();
        if (!message.isEmpty()) {
            MainApplication.speakOut(SpellUtil.convertToSpellOnce(message));
        } else {
            Toast.makeText(this, "Empty Message", Toast.LENGTH_SHORT).show();
        }
    }


}
