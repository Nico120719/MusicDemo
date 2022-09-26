package com.example.musicdemotest.Utils;


import android.content.Context;

import android.os.Environment;

import android.widget.Toast;

import com.example.musicdemotest.R;

import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Download {


    public static void saveSample(Context context, int resId, String title)  {


        try {

            InputStream inputStream = context.getResources().openRawResource(resId);

            File directory = new File(Environment.getExternalStorageDirectory()

                    .getAbsolutePath() + "/Download/Marco_&_Nico_Samples/");

            if (!directory.exists()) directory.mkdir();

            File sample = new File(directory, title + ".wav");

            FileOutputStream fileOutputStream = new FileOutputStream(sample);

            byte[] buf = new byte[1024];

            int len;

            while((len = inputStream.read(buf)) > 0) {

                fileOutputStream.write(buf,0, len);
            }

            inputStream.close();

            fileOutputStream.flush();

            fileOutputStream.close();

            Toast.makeText(context, context.getString(R.string.downloadsuccessful), Toast.LENGTH_LONG).show();

        } catch (IOException e) {

            e.printStackTrace();

            Toast.makeText(context, context.getString(R.string.downloadfailed), Toast.LENGTH_LONG).show();
        }
    }
}
