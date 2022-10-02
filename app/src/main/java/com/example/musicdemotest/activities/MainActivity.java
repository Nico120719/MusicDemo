package com.example.musicdemotest.activities;


import com.example.musicdemotest.R;
import com.example.musicdemotest.models.MusicPlayer;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;

import android.media.MediaPlayer;

import android.os.Build;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.io.IOException;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private MediaPlayer mediaPlayer;

    private boolean wasPaused;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mediaPlayer = MusicPlayer.getInstance().getMediaPlayer();

        List<Button> buttons = List.of(

                findViewById(R.id.stringsButton),

                findViewById(R.id.hornsButton),

                findViewById(R.id.drumsButton),

                findViewById(R.id.synthsButton),

                findViewById(R.id.worldButton));

        int textSize = 34;

        int THRESHOLD = 7;

        int MIN_TEXT_SIZE = 24;

        for (Button button : buttons) if (button.getText().toString().length() > THRESHOLD)

            textSize = MIN_TEXT_SIZE;

        for (Button button : buttons)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)

                button.setTextSize(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM,textSize);
    }

    @Override
    protected void onStart() {


        super.onStart();

        if (!wasPaused) {

            AssetFileDescriptor assetFileDescriptor = getResources().openRawResourceFd(R.raw.intro);

            onStartMusic(mediaPlayer, assetFileDescriptor);

            mediaPlayer.setOnCompletionListener(MediaPlayer::reset);
        }
    }


    @Override
    protected void onPause() {


        super.onPause();

        mediaPlayer.reset();

        wasPaused = true;
    }


    public static void onStartMusic(MediaPlayer mediaPlayer, AssetFileDescriptor assetFileDescriptor) {


        try {

            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),

                    assetFileDescriptor.getStartOffset(),

                    assetFileDescriptor.getLength());

            assetFileDescriptor.close();

            mediaPlayer.prepare();

            mediaPlayer.start();


        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    public void onAfficher(View view) {


        String categorie = ((Button) view).getText().toString();

        Intent intent = new Intent(this, ListActivity.class);

        intent.putExtra("categorie", categorie);

        startActivity(intent);
    }
}
