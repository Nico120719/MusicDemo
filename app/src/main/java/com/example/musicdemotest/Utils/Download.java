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


    public static void saveSample(Context context, int resId, String nom)  {


        try {

            InputStream inputStream = context.getResources().openRawResource(resId);

            File directory = new File(Environment.getExternalStorageDirectory()

                    .getAbsolutePath() + "/Download/Marco_&_Nico_Samples/");

            if (!directory.exists()) directory.mkdir();

            File sample = new File(directory, nom);

            FileOutputStream fileOutputStream = new FileOutputStream(sample);

            byte[] buffer = new byte[1024];

            int length;

            while((length = inputStream.read(buffer)) > 0) {

                fileOutputStream.write(buffer,0, length);
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
