package com.example.musicdemotest.Utils;


import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.example.musicdemotest.R;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class Download {

    private static void saveSample(Context context) throws IOException {


        InputStream path = context.getResources().openRawResource(R.raw.strings);

        BufferedInputStream bufferedInputStream = new BufferedInputStream(path,1024);

        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(1024);

        int current;

        while ((current = bufferedInputStream.read()) != -1) {

            byteArrayBuffer.append((byte) current);
        }

        byte[] bitmapdata  = byteArrayBuffer.toByteArray();


        File file = new File(Environment.getExternalStorageDirectory(), "sample.mp3");

        if (!file.exists())

            if (!file.mkdirs())

                Toast.makeText(context,

                        "Impossible de créer le Répertoire ...", Toast.LENGTH_LONG).show();

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        try {

            fileOutputStream.write(bitmapdata);

            fileOutputStream.flush();

            fileOutputStream.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
