package com.example.musicdemotest.activities;


import com.example.musicdemotest.R;
import com.example.musicdemotest.models.MusicPlayer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;

import android.media.MediaPlayer;

import android.os.Build;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private MediaPlayer mediaPlayer;

    private boolean wasPaused;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setTheme(R.style.MenuTheme);

        setContentView(R.layout.activity_main);

        mediaPlayer = MusicPlayer.getInstance().getMediaPlayer();

        List<Button> buttons = List.of(

                findViewById(R.id.stringsButton),

                findViewById(R.id.hornsButton),

                findViewById(R.id.drumsButton),

                findViewById(R.id.synthsButton),

                findViewById(R.id.worldButton),

                findViewById(R.id.voiceButton));

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
    public boolean onCreateOptionsMenu(Menu menu) {


        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_detail, menu);

        MenuItem menuAdd = menu.findItem(R.id.ajouter);

        menuAdd.setVisible(false);

        MenuItem menuDelete = menu.findItem(R.id.supprimer);

        menuDelete.setVisible(false);

        MenuItem menuBack = menu.findItem(R.id.back);

        menuBack.setVisible(false);

        MenuItem menuCollection = menu.findItem(R.id.collection);

        menuCollection.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        MenuItem aboutUs = menu.findItem(R.id.about);

        aboutUs.collapseActionView();

        return true;
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        int option = item.getItemId();

        switch (option) {

            case R.id.collection:

                Toast.makeText(MainActivity.this, R.string.collection,

                Toast.LENGTH_SHORT).show();

                break;


            case R.id.about:

                startActivity(new Intent(MainActivity.this, AboutUsActivity.class));

        }

        return super.onOptionsItemSelected(item);
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
