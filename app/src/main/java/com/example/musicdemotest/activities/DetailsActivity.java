package com.example.musicdemotest.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.res.AssetFileDescriptor;

import android.os.Bundle;
import android.os.Handler;

import android.view.View;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.musicdemotest.*;
import com.example.musicdemotest.Utils.Download;
import com.example.musicdemotest.models.MyMediaPlayer;
import com.google.android.material.button.MaterialButton;

import java.io.IOException;


public class DetailsActivity extends AppCompatActivity  {


    private String title;

    private MyMediaPlayer mediaPlayer;

    private MaterialButton playButton;

    private ProgressBar progressBar;

    boolean isPaused;

    private Handler handler;


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

        playButton = (MaterialButton) findViewById(R.id.playButton);

        progressBar = findViewById(R.id.progressBar);

        handler = new Handler();

        TextView type = findViewById(R.id.type);

        type.setText(title);
    }


    private void setListeners() {


        mediaPlayer.setOnPreparedListener(mediaPlayer ->

                progressBar.setMax(mediaPlayer.getDuration()));
    }

    @SuppressLint("MissingPermission")
    public void onPlayMusic(View view) throws IOException {

        if (!isPaused && !mediaPlayer.isPlaying()) {

            int resId = getResources()

                    .getIdentifier(translateString(title).toLowerCase(), "raw", getPackageName());

            try (AssetFileDescriptor assetFileDescriptor = getResources().openRawResourceFd(resId)) {

                try {

                    mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),

                            assetFileDescriptor.getStartOffset(),

                            assetFileDescriptor.getLength());

                    assetFileDescriptor.close();

                    mediaPlayer.prepare();

                    mediaPlayer.start();

                    mediaPlayer.setLooping(false);

                    playButton.setIcon(ContextCompat.getDrawable(this, android.R.drawable.ic_media_pause));

                    updateProgressbar();

//                    mediaPlayer.setOnCompletionListener(MediaPlayer::reset);

                    mediaPlayer.setOnCompletionListener(mediaPlayer -> {

                        mediaPlayer.reset();

                        playButton.setIcon(ContextCompat.getDrawable(getApplicationContext(),

                                android.R.drawable.ic_media_play));

                    });

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

        } else if (isPaused) {

            mediaPlayer.start();

            playButton.setIcon(ContextCompat.getDrawable(this, android.R.drawable.ic_media_pause));

            isPaused = false;

        } else if (mediaPlayer.isPlaying()) {

            mediaPlayer.pause();

            playButton.setIcon(ContextCompat.getDrawable(this, android.R.drawable.ic_media_play));

            isPaused = true;
        }
    }


    private void updateProgressbar() {


        int currentPosition = mediaPlayer.getCurrentPosition();

        progressBar.setProgress(currentPosition);

        Runnable runnable = this::updateProgressbar;

        handler.postDelayed(runnable, 1);
}


    @Override
    protected void onPause() {

        super.onPause();

        mediaPlayer.reset();
    }


    @SuppressLint("MissingPermission")
    public void onDownload(View view) {

        int resId = getResources()

                .getIdentifier(translateString(title).toLowerCase(), "raw", getPackageName());

        Download.saveSample(this, resId, translateString(title));
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
