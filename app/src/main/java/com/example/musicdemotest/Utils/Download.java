package com.example.musicdemotest.Utils;


import android.content.Context;

import android.os.Environment;

import android.widget.Toast;

import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Download {


    public static void saveSample(Context context, int resId, String title)  {


        try {

            InputStream inputStream = context.getResources().openRawResource(resId);

            FileOutputStream fileOutputStream = new FileOutputStream

                    (Environment.getExternalStorageDirectory()

                            .getAbsolutePath() + "/Download/" + title + ".wav");

            byte[] buf = new byte[1024];

            int len;

            while((len = inputStream.read(buf)) > 0) {

                fileOutputStream.write(buf,0, len);
            }

            inputStream.close();

            fileOutputStream.flush();

            fileOutputStream.close();

            Toast.makeText(context, "Audio file downloaded to Internal Storage !", Toast.LENGTH_LONG).show();


        } catch (IOException e) {

            e.printStackTrace();

            Toast.makeText(context, "Could not download audio file !", Toast.LENGTH_LONG).show();
        }
    }
}
