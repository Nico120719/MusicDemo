package com.example.musicdemotest.activities;


import com.example.musicdemotest.R;
import com.example.musicdemotest.models.MusicPlayer;
import com.example.musicdemotest.models.Sample;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;

import android.graphics.Color;
import android.media.MediaPlayer;

import android.os.Bundle;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;


 /**

 * Nicolas Ineksiak - Marko Perotic

 * Database MusicSamples stockée dans le répertoire assets/databases de vette application

 * Échantillons de musique gratuits provenant de https://sampleswap.org

 */


public class MainActivity extends AppCompatActivity {


    private MediaPlayer mediaPlayer;

    private boolean wasPaused;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        //setTheme(R.style.MenuTheme);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Initialisation du Singleton MediaPlayer */

        mediaPlayer = MusicPlayer.getInstance().getMediaPlayer();


    }




     /* Options de la barre de Menus pour cette Activité */

    /* Collection et À Propos seulement */

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
        SpannableString s = new SpannableString(aboutUs.getTitle());
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, s.length(), 0);
        aboutUs.setTitle(s);
        aboutUs.collapseActionView();

        return true;
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        int option = item.getItemId();

        switch (option) {

            /* Déterminera la liste Collection à afficher */

            case R.id.collection:

                Intent intent = new Intent(MainActivity.this, ListActivity.class);

                intent.putExtra("collection", true);

                startActivity(intent);

                break;

            /* Redirection vers l'Activité À Propos */

            case R.id.about:

                startActivity(new Intent(MainActivity.this, AboutUsActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {


        super.onStart();

        if (!wasPaused) {

            /* Recherche de la Ressource de l'échantillon musical de la page de départ */

            AssetFileDescriptor assetFileDescriptor = getResources().openRawResourceFd(R.raw.intro);


            /* Appel à la méthode de déclenchement de la musique */

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


    /* Initialisation de la musique d'introduction */

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


        /* Recherche la catégorie musicale définie par le click du bouton

        /* et envoie l'information à l'Activité ListActivity */

        String categorie = ((Button) view).getText().toString();


        Intent intent = new Intent(this, ListActivity.class);

        intent.putExtra("categorie", Sample.translateString(categorie).toLowerCase());


        startActivity(intent);
    }
}
