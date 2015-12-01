package com.possiblelabs.audiotest;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by possiblelabs on 7/23/15.
 */
public class FileUtil {

    public static void makeDir(String diretoryPath) {
        File directory = new File(diretoryPath);
        if (!directory.exists())
            directory.mkdir();
    }

    public static File getPath(Activity ref, Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = ref.managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return new File(cursor.getString(column_index));
    }

    public static void copy(File src, File dst) {
        try {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dst);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
