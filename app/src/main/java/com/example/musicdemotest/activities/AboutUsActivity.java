package com.example.musicdemotest.activities;


import com.example.musicdemotest.R;
import com.example.musicdemotest.models.MusicPlayer;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;


public class AboutUsActivity extends AppCompatActivity {


    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about_us);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setWidgets();
    }


    private void setWidgets() {


        /* Initialisation du Singleton MediaPlayer */

        mediaPlayer = MusicPlayer.getInstance().getMediaPlayer();


        /* Appel à la méthode qui démarre la musique */

        outro(mediaPlayer);


        /* Remerciements */

        TextView thankYou = findViewById(R.id.thanks);

        thankYou.setText(R.string.thanks);
    }


    /* Options de la barre de Menus pour cette Activité */

    /* Home et À Retour (<-) seulement */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_detail, menu);


        MenuItem menuAdd = menu.findItem(R.id.ajouter);

        menuAdd.setVisible(false);


        MenuItem menuDelete = menu.findItem(R.id.supprimer);

        menuDelete.setVisible(false);


        MenuItem menuCollection = menu.findItem(R.id.collection);

        menuCollection.setVisible(false);


        MenuItem menuBack = menu.findItem(R.id.back);

        menuBack.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);


        MenuItem aboutUs = menu.findItem(R.id.about);

        aboutUs.setVisible(false);

        return true;
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        int option = item.getItemId();

        switch (option) {

            /* Retour à l'Activité MainActivity ( Home ) */

            case R.id.home:

                startActivity(new Intent(AboutUsActivity.this, MainActivity.class));

                break;


            case R.id.back:

                finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPause() {


        super.onPause();

        mediaPlayer.reset();
    }


    /* Redémarrage de la musique au retour à l'Activité */

    @Override
    protected void onRestart() {


        super.onRestart();

        outro(mediaPlayer);

    }


    /* Méthode de démarrage de la musique */

    private void outro(MediaPlayer mediaPlayer) {


        AssetFileDescriptor assetFileDescriptor = getResources().openRawResourceFd(R.raw.braincandy);

        MainActivity.onStartMusic(mediaPlayer, assetFileDescriptor);

        mediaPlayer.setOnCompletionListener(MediaPlayer::reset);
    }
}
