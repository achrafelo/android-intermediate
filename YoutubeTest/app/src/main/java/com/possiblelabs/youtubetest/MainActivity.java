package com.possiblelabs.youtubetest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;


public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener,
        YouTubePlayer.PlaybackEventListener, YouTubePlayer.PlayerStateChangeListener {

    private static final String TAG = "YT_TEST";
    private static final int VIDEO_TYPE = 0;
    private static final int CHANNEL_TYPE = 1;
    public static final String DF_YOUTUBE_VIDEO_ID = "YlGekbR2VJE";

    private EditText code;
    private RadioGroup type;
    private String apiKey;
    private YouTubePlayerView ytpv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        code = (EditText) findViewById(R.id.txt_code);
        type = (RadioGroup) findViewById(R.id.radio_group);
        apiKey = getResources().getString(R.string.youtube_api_key);
        ytpv = (YouTubePlayerView) findViewById(R.id.youtube_player);
    }

    private int getType() {
        if (type.getCheckedRadioButtonId() == R.id.radio_channel)
            return CHANNEL_TYPE;
        return VIDEO_TYPE;
    }

    private String getCode() {
        String res = code.getText().toString();
        if (res.isEmpty())
            return DF_YOUTUBE_VIDEO_ID;
        return res;
    }

    public void playVideo(View view) {
        ytpv.initialize(apiKey, this);
    }

    public void openYoutubeVideo(View view) {
        Intent intent = null;
        int typeCode = getType();

        if (typeCode == VIDEO_TYPE)
            intent = YouTubeStandalonePlayer.createVideoIntent(this, apiKey, getCode());
        else if (typeCode == CHANNEL_TYPE)
            intent = YouTubeStandalonePlayer.createPlaylistIntent(this, apiKey, getCode());

        if (intent != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        youTubePlayer.setPlayerStateChangeListener(this);
        youTubePlayer.setPlaybackEventListener(this);

        int typeCode = getType();

        if (wasRestored)
            return;

        if (typeCode == VIDEO_TYPE)
            youTubePlayer.cueVideo(getCode());
        else if (typeCode == CHANNEL_TYPE)
            youTubePlayer.cuePlaylist(getCode());
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Error during youtube player initialization", Toast.LENGTH_SHORT).show();
        youTubeInitializationResult.getErrorDialog(this, 1).show();
    }

    //PlaybackEventListener
    @Override
    public void onPlaying() {
//        Log.d(TAG, "onPlaying");
    }

    @Override
    public void onPaused() {
//        Log.d(TAG, "onPaused");
    }

    @Override
    public void onStopped() {
//        Log.d(TAG, "onStopped");
    }

    @Override
    public void onBuffering(boolean b) {
//        Log.d(TAG, "onBuffering:" + b);
    }

    @Override
    public void onSeekTo(int i) {
//        Log.d(TAG, "onSeekTo:" + i);
    }


    //PlayerStateChangeListener
    @Override
    public void onLoading() {
//        Log.d(TAG, "onLoading");
    }

    @Override
    public void onLoaded(String s) {
//        Log.d(TAG, "onLoaded:" + s);
    }

    @Override
    public void onAdStarted() {
//        Log.d(TAG, "onAdStarted");
    }

    @Override
    public void onVideoStarted() {
//        Log.d(TAG, "onVideoStarted");
    }

    @Override
    public void onVideoEnded() {
//        Log.d(TAG, "onVideoEnded");
    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {
//        Log.d(TAG, "onError:" + errorReason.name());
    }
}
