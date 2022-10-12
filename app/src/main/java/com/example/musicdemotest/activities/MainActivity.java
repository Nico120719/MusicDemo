package com.example.musicdemotest.activities;


import com.example.musicdemotest.R;
import com.example.musicdemotest.models.MusicPlayer;
import com.example.musicdemotest.models.Sample;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;

import android.media.MediaPlayer;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {


    private MediaPlayer mediaPlayer;

    private boolean wasPaused;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setTheme(R.style.MenuTheme);

        setContentView(R.layout.activity_main);

        mediaPlayer = MusicPlayer.getInstance().getMediaPlayer();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_detail, menu);

        MenuItem home = menu.findItem(R.id.home);

        home.setVisible(false);

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

                Intent intent = new Intent(MainActivity.this, ListActivity.class);

                intent.putExtra("collection", true);

                startActivity(intent);

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

        intent.putExtra("categorie", Sample.translateString(categorie).toLowerCase());

        startActivity(intent);
    }
}
