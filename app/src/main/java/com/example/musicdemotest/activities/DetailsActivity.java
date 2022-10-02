package com.example.musicdemotest.activities;

import com.example.musicdemotest.R;
import com.example.musicdemotest.Utils.Download;
import com.example.musicdemotest.models.MusicPlayer;

import android.annotation.SuppressLint;

import android.content.res.AssetFileDescriptor;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;

import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;


public class DetailsActivity extends AppCompatActivity  {


    private String sample;

    private String categorie;

    private String sampleRootName;

    private MediaPlayer mediaPlayer;

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


        sample = getIntent().getStringExtra("sample");

        categorie = getIntent().getStringExtra("categorie");

        sampleRootName = sample.substring(0, sample.length() - 4);

        mediaPlayer = MusicPlayer.getInstance().getMediaPlayer();

        playButton = (MaterialButton) findViewById(R.id.playButton);

        progressBar = findViewById(R.id.progressBar);

        handler = new Handler();

        TextView nom = findViewById(R.id.nom);

        nom.setText(sample);
    }


    private void setListeners() {


        mediaPlayer.setOnPreparedListener(mediaPlayer ->

                progressBar.setMax(mediaPlayer.getDuration()));
    }


    public void onPlayMusic(View view) {

        if (!isPaused && !mediaPlayer.isPlaying()) {

            int resId = getResources()

                    .getIdentifier(sampleRootName, "raw", getPackageName());

            AssetFileDescriptor assetFileDescriptor = getResources().openRawResourceFd(resId);

            MainActivity.onStartMusic(mediaPlayer, assetFileDescriptor);

            playButton.setIcon(ContextCompat.getDrawable(this, android.R.drawable.ic_media_pause));

            updateProgressbar();

            mediaPlayer.setOnCompletionListener(mediaPlayer -> {

                mediaPlayer.reset();

                playButton.setIcon(ContextCompat.getDrawable(getApplicationContext(),

                        android.R.drawable.ic_media_play));
            });

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

                .getIdentifier(sampleRootName, "raw", getPackageName());

        Download.saveSample(this, resId, sample, categorie);
    }
}
