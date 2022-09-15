package com.example.musicdemotest;


import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;

import android.os.Bundle;

import android.view.View;

import android.widget.TextView;

import android.media.MediaPlayer;

import java.io.IOException;


public class DetailsActivity extends AppCompatActivity {


    private String title;

    MyMediaPlayer mediaPlayer;

    boolean isPaused;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_details);

        setWidgets();
    }


    private void setWidgets() {


        Bundle bundle = getIntent().getExtras();

        title = bundle.getString("instrument");

        mediaPlayer = bundle.getParcelable("mediaplayer");

        TextView type = findViewById(R.id.type);

        type.setText(title);
    }


    public void onPlayMusic(View view) {


        if (!isPaused) {

            int resId = getResources()

                    .getIdentifier(translateString(title).toLowerCase(), "raw", getPackageName());

            AssetFileDescriptor assetFileDescriptor = getResources().openRawResourceFd(resId);

            try {

                mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),

                        assetFileDescriptor.getStartOffset(),

                        assetFileDescriptor.getLength());

                assetFileDescriptor.close();

                mediaPlayer.prepare();

                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(MediaPlayer::reset);

            } catch (IOException e) {

                e.printStackTrace();
            }

        } else {

            mediaPlayer.start();

            isPaused = false;
        }
    }


    public void onPauseMusic(View view) {


        if (mediaPlayer.isPlaying()) {

            mediaPlayer.pause();

            isPaused = true;
        }
    }


    public void onStopMusic(View view) {


        if (mediaPlayer.isPlaying()) {

            mediaPlayer.reset();
        }
    }


    @Override
    protected void onPause() {

        super.onPause();

        mediaPlayer.reset();
    }


    private String translateString(String title) {


        if (title.equalsIgnoreCase("cordes")) title = "strings";

        if (title.equalsIgnoreCase("vents")) title = "horns";

        if (title.equalsIgnoreCase("percussions")) title = "drums";

        if (title.equalsIgnoreCase("claviers")) title = "synths";

        if (title.equalsIgnoreCase("monde")) title = "world";

        return title;
    }
}
