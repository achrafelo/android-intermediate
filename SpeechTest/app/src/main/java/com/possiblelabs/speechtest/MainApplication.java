package com.possiblelabs.speechtest;

import android.app.Application;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import java.util.HashMap;
import java.util.Locale;
import java.util.Set;

/**
 * Created by possiblelabs on 7/25/15.
 */
public class MainApplication extends Application implements TextToSpeech.OnInitListener {

    private static final String TAG = "MainApplication";
    private static TextToSpeech tts;
    private static HashMap<String, String> map = new HashMap<String, String>();
    private static final short status = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        tts = new TextToSpeech(this, this);
    }

    public static void linkListener(final Talking talking) {
        tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {

            @Override
            public void onStart(String s) {
                talking.start(s);
            }

            @Override
            public void onDone(String s) {
                talking.stop(s);
            }

            @Override
            public void onError(String s) {

            }
        });
    }


    @Override
    public void onInit(int i) {
        if (status == TextToSpeech.SUCCESS) {
//            Locale locSpanish = new Locale("spa", "MEX");
            Locale locSpanish = new Locale("spa", "ESP");
            Locale locEnglish = new Locale("en");

            if (Build.VERSION.SDK_INT >= 21) {
                Set<Locale> locales = tts.getAvailableLanguages();
                if (locales != null) {
                    for (Locale locale : locales) {
                        Log.d(TAG, locale.getCountry() + " => " + locale.getLanguage());
                    }
                }
            }

            Locale locale = Locale.getDefault();
            Log.d(TAG, "DEFAULT: " + locale.getCountry() + " => " + locale.getLanguage());

            int result = tts.setLanguage(locEnglish);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.d(TAG, "This Language is not supported");
            }else{
                Log.d(TAG, "TTS ready");
            }

        } else {
            Log.d(TAG, "Text to Speech Init Failed!");
        }
    }

    @Override
    public void onTerminate() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onTerminate();
    }

    public static void speakOut(String text) {
        try {
            if (tts != null) {
                Log.d(TAG, text);
                map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "UniqueID");
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, map);
            } else {
                Log.d(TAG, "SpeakOut not working");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "SpeakOut not working: " + e.getMessage());
        }
    }

}
