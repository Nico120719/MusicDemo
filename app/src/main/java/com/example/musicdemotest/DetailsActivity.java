package com.example.musicdemotest;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.TextView;

import android.content.Intent;

import android.media.MediaPlayer;


public class DetailsActivity extends AppCompatActivity {


    private String title;

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_details);

        setWidgets();
    }


    private void setWidgets() {


        Intent intent = getIntent();

        title = intent.getStringExtra("instrument");

        TextView type = findViewById(R.id.type);

        type.setText(title);
    }


    public void onPlayMusic(View view) {


        if (mediaPlayer == null)

            mediaPlayer = MediaPlayer.create(DetailsActivity.this , getResources()

                    .getIdentifier(title.toLowerCase(), "raw", getPackageName()));


        assert mediaPlayer != null;

        mediaPlayer.start();
    }


    public void onPauseMusic(View view) {


        if (mediaPlayer != null) mediaPlayer.pause();
    }


    public void onStopMusic(View view) {


        if (mediaPlayer != null) {

            mediaPlayer.release();

            mediaPlayer = null;
        }
    }
}
