package com.possiblelabs.cameratest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements Constants, AdapterView.OnItemClickListener {

    private String directoryPath = Environment.getExternalStorageDirectory() + SEPARATOR + APP_DIRECTORY;
    private String photoFileName = directoryPath + SEPARATOR;
    private ListView listView;
    private List<String> fileNames;

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
        File[] files = getPhotoFilesFromDirectory();
        for (int i = 0; i < files.length; i++)
            fileNames.add(files[i].getAbsolutePath());
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fileNames));
        listView.setOnItemClickListener(this);
    }

    public void doTakePicture(View view) {
        File file = new File(getPhotoFileName());
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
                loadFileNames();
            } else if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                File selectedFile = FileUtil.getPath(MainActivity.this, selectedImageUri);
                File destinyFile = new File(getPhotoFileName());
                FileUtil.copy(selectedFile, destinyFile);
                loadFileNames();
            }
        }
    }

    private String getPhotoFileName() {
        return photoFileName + StringUtil.genFileName(IMAGE_EXTENSION);
    }

    private File[] getPhotoFilesFromDirectory() {
        File file = new File(directoryPath);
        return file.listFiles();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        File imgFile = new File(fileNames.get(position));
        if (imgFile.exists() && imgFile.isFile()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ImageView myImage = (ImageView) findViewById(R.id.imageView);
            myImage.setImageBitmap(myBitmap);
        }
    }
}
