package com.example.musicdemotest.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;

import android.os.Bundle;
import android.os.Handler;

import android.view.View;

import android.widget.ProgressBar;
import android.widget.TextView;

import android.media.MediaPlayer;

import com.example.musicdemotest.models.MyMediaPlayer;
import com.example.musicdemotest.R;

import java.io.IOException;


public class DetailsActivity extends AppCompatActivity  {


    private String title;

    MyMediaPlayer mediaPlayer;

    ProgressBar progressBar;

    boolean isPaused;

    Runnable runnable;

    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_details);

        setWidgets();

        setListeners();
    }


    private void setWidgets() {


        Bundle bundle = getIntent().getExtras();

        title = bundle.getString("instrument");

        mediaPlayer = bundle.getParcelable("mediaplayer");

        progressBar = findViewById(R.id.progressBar);

        handler = new Handler();

        TextView type = findViewById(R.id.type);

        type.setText(title);
    }


    private void setListeners() {


        mediaPlayer.setOnPreparedListener(mediaPlayer ->

                progressBar.setMax(mediaPlayer.getDuration()));
    }

    public void onPlayMusic(View view) {

        if (!isPaused && !mediaPlayer.isPlaying()) {

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

                mediaPlayer.setLooping(false);

                updateProgressbar();

                mediaPlayer.setOnCompletionListener(MediaPlayer::reset);

            } catch (IOException e) {

                e.printStackTrace();
            }

        } else if (isPaused) {

            mediaPlayer.start();

            isPaused = false;
        }
    }


    private void updateProgressbar() {


        int currentPosition = mediaPlayer.getCurrentPosition();

        progressBar.setProgress(currentPosition);

        runnable = this::updateProgressbar;

        handler.postDelayed(runnable, 1);
}


    public void onPauseMusic(View view) {


        if (mediaPlayer.isPlaying()) {

            mediaPlayer.pause();

            isPaused = true;
        }
    }


    public void onStopMusic(View view) {


        if (mediaPlayer.isPlaying() || isPaused) {

            mediaPlayer.reset();

            isPaused = false;
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
