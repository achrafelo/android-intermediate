package com.possiblelabs.speechtest;

import android.app.Application;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Arrays;
import java.util.Locale;

/**
 * Created by possiblelabs on 7/25/15.
 */
public class MainApplication extends Application implements TextToSpeech.OnInitListener {

    private static final String TAG = "";
    private static TextToSpeech tts;
    private static final short status = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        tts = new TextToSpeech(this, this);
    }


    @Override
    public void onInit(int i) {
        if (status == TextToSpeech.SUCCESS) {


//            Locale locSpanish = new Locale("spa", "MEX");
            Locale locSpanish = new Locale("spa", "ESP");
            Locale locEnglish = new Locale("en");

            Log.i("-----", tts.isLanguageAvailable(locSpanish) + "");
            Log.i("-----", Arrays.toString(Locale.getAvailableLocales()));

            int result = tts.setLanguage(locEnglish);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.d(TAG, "This Language is not supported");
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
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            } else {
                Log.d(TAG, "SpeakOut not working");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "SpeakOut not working: " + e.getMessage());
        }
    }
}
