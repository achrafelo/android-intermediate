package com.possiblelabs.speechtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    private EditText txtMesage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMesage = (EditText) findViewById(R.id.txtMessage);
    }

    public void doLoad(View view) {
        //DOCS => http://api.wikia.com/wiki/LyricWiki_API/REST
        //1) Load all songs
        //2) Load from URL
        //http://lyrics.wikia.com/api.php?func=getSong&artist=Linkin_Park&fmt=realjson
        //http://lyrics.wikia.com/api.php?artist=Linkin_Park&song=A_Light_That_Never_Comes&fmt=realjson
        txtMesage.setText("Nah, you don't know me Lightning above and a fire below me You can not catch me, can not hold me You can not stop, much less control me When it rains, it pours When the floodgates open, brace your shores That pressure don't care when it break[...]");
    }

    public void doTextToSpeech(View view) {
        String message = txtMesage.getText().toString();
        if (!message.isEmpty()) {
            MainApplication.speakOut(message);
        } else {
            Toast.makeText(this, "Empty Message", Toast.LENGTH_SHORT).show();
        }
    }


}
