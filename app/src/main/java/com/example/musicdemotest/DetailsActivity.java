package com.example.musicdemotest;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import android.view.View;

import android.widget.TextView;

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


        Bundle bundle = getIntent().getExtras();

        title = bundle.getString("instrument");

        mediaPlayer = bundle.getParcelable("mediaplayer");

        TextView type = findViewById(R.id.type);

        type.setText(title);
    }


    public void onPlayMusic(View view) {

        if (mediaPlayer == null)

            mediaPlayer =  MediaPlayer.create(DetailsActivity.this , getResources()

                .getIdentifier(translateString(title).toLowerCase(), "raw", getPackageName()));

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


    private String translateString(String title) {


        if (title.equalsIgnoreCase("cordes")) title = "strings";

        if (title.equalsIgnoreCase("vents")) title = "horns";

        if (title.equalsIgnoreCase("percussions")) title = "drums";

        if (title.equalsIgnoreCase("claviers")) title = "synths";

        if (title.equalsIgnoreCase("monde")) title = "world";

        return title;
    }
}
