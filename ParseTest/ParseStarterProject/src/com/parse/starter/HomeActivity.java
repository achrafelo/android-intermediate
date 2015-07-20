package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class HomeActivity extends Activity implements Constants {


    private String diretoryPath = Environment.getExternalStorageDirectory() + SEPARATOR + APP_DIRECTORY;
    private String photoFileName = diretoryPath + SEPARATOR + IMAGE_TEMP_NAME;
    private static final String TAG = "PSPA";

    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

    public void onStart() {
        super.onStart();
        makeDir();
    }

    private void testParse() {
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Place");
        query.whereEqualTo("enable", true);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> placeList, ParseException e) {
                if (e == null) {
                    for (ParseObject place : placeList) {
                        ParseGeoPoint gp = place.getParseGeoPoint("position");
                        String name = place.getString("name");

                        Log.d(TAG, "==========");
                        Log.d(TAG, "GP:" + gp.toString());
                        Log.d(TAG, "Name:" + name);

                    }
                    Log.d(TAG, "Retrieved " + placeList.size() + " scores");
                } else {
                    Log.d(TAG, "Error: " + e.getMessage());
                }
            }
        });
    }

    public void doTakePitcture(View view) {

        File file = new File(photoFileName);
        Uri outputFileUri = Uri.fromFile(file);

        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(intent, CAMERA_REQUEST);

    }

    public void doSelectPicture(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "Seleccione su Foto"), SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Constants.RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                uploadToParse();
            } else if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                copy(getPath(selectedImageUri), new File(photoFileName));
                uploadToParse();
            }
        }
    }

    public void uploadToParse() {
        File file = new File(photoFileName);

        byte[] image = readInFile(file);
        Log.d(TAG, "IMAGE:" + image.toString());
        Log.d(TAG, "IMAGE:" + image.length);
        ParseFile pfile = new ParseFile(IMAGE_TEMP_NAME, image);
        pfile.saveInBackground();


        ParseObject imgupload = new ParseObject("Image");
        imgupload.put("Image", "photo");
        imgupload.put("ImageFile", pfile);
        imgupload.saveInBackground();
    }

    public File getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return new File(cursor.getString(column_index));
    }


    public byte[] readInFile(File file) {

        try {
            // TODO Auto-generated method stub
            byte[] data = null;
            InputStream input_stream = new BufferedInputStream(new FileInputStream(file));
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            data = new byte[16384]; // 16K
            int bytes_read;
            while ((bytes_read = input_stream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, bytes_read);
            }
            input_stream.close();
            Log.d(TAG, "error during copy");
            return buffer.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "error during copy");
            return null;
        }
    }

    public void copy(File src, File dst) {

        try {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dst);

            // Transfer bytes from in to out
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            Log.d(TAG, "success copied");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "error during copy");
        }

    }

    private void makeDir() {
        File directory = new File(diretoryPath);
        if (!directory.exists())
            directory.mkdir();
    }

}
