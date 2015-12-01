package com.possiblelabs.audiotest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements Constants, AdapterView.OnItemClickListener {

    private String directoryPath = Environment.getExternalStorageDirectory() + SEPARATOR + APP_DIRECTORY;
    private String audioFileName = directoryPath + SEPARATOR;
    private ListView listView;
    private List<String> fileNames;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FileUtil.makeDir(directoryPath);
        fileNames = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
        loadFileNames();
    }

    private void loadFileNames() {
        listView.setOnItemClickListener(null);
        fileNames.clear();
        File[] files = getAudioFilesFromDirectory();
        for (int i = 0; i < files.length; i++)
            fileNames.add(files[i].getAbsolutePath());
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fileNames));
        listView.setOnItemClickListener(this);
    }

    public void doRecordAudio(View view) {
        recordAudio(getAudioFileName(), StringUtil.genFileName(AUDIO_EXTENSION));
    }

    public void recordAudio(String fileName, String nameRecord) {
        final MediaRecorder recorder = new MediaRecorder();
        ContentValues values = new ContentValues(1);
        values.put(MediaStore.MediaColumns.TITLE, nameRecord);
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        recorder.setOutputFile(fileName);
        try {
            recorder.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

        final ProgressDialog mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setTitle(R.string.lbl_recording);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setButton("Stop recording", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mProgressDialog.dismiss();
                recorder.stop();
                recorder.release();
                loadFileNames();
            }
        });

        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface p1) {
                recorder.stop();
                recorder.release();
                loadFileNames();
            }
        });
        recorder.start();
        mProgressDialog.show();
    }


    public void doSelectAudio(View view) {
        Intent intent = new Intent();
        intent.setType("audio/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccione su Audio"), SELECT_AUDIO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Constants.RESULT_OK) {
            if (requestCode == SELECT_AUDIO) {
                Uri selectedAudioUri = data.getData();
                File selectedFile = FileUtil.getPath(MainActivity.this, selectedAudioUri);
                File destinyFile = new File(getAudioFileName());
                FileUtil.copy(selectedFile, destinyFile);
                loadFileNames();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String getAudioFileName() {
        return audioFileName + StringUtil.genFileName(AUDIO_EXTENSION);
    }

    private File[] getAudioFilesFromDirectory() {
        File file = new File(directoryPath);
        return file.listFiles();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        File audioFile = new File(fileNames.get(position));
        if (audioFile.exists() && audioFile.isFile()) {

            mp = new MediaPlayer();

            try {
                mp.setDataSource(audioFile.getAbsolutePath());
                mp.prepare();
                mp.start();
                mp.setLooping(false);
                Toast.makeText(MainActivity.this, "Reproduciendo", Toast.LENGTH_SHORT).show();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        Toast.makeText(MainActivity.this, "Se termino", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "No se puede reproducir", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
