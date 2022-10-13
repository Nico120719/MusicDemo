package com.example.musicdemotest.activities;


import com.example.musicdemotest.R;
import com.example.musicdemotest.Utils.Download;
import com.example.musicdemotest.database.SampleBDAdapter;
import com.example.musicdemotest.models.MusicPlayer;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;

import android.graphics.Color;
import android.media.MediaPlayer;

import android.os.Bundle;
import android.os.Handler;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;


import com.google.android.material.button.MaterialButton;


/* Affiche l'échatillon sélectionné dans ListActivity avec bouton Play, ProgressBar, durée et */

/* possibilité de téléchargement sur le stockage interne du téléphone */

public class DetailsActivity extends AppCompatActivity  {


    private String sample;

    private String sampleRootName;

    private String categorie;

    private MediaPlayer mediaPlayer;

    private MaterialButton playButton;

    private ProgressBar progressBar;

    boolean isPaused;

    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setWidgets();

        setListeners();
    }


    private void setWidgets() {


        /* Récupère le nom de l'échantillon */

        sample = getIntent().getStringExtra("sample");

        sampleRootName = sample.substring(0, sample.length() - 4);


        /* Récupère la catégorie pour afficher l'image appropriée */

        categorie = getIntent().getStringExtra("categorie");


        /* Récupère la Ressource de l'image */

        int resId = getResources()

                   .getIdentifier(categorie, "drawable", getPackageName());

        ImageView image = findViewById(R.id.image);

        image.setImageResource(resId);


        /* Instanciation du Singleton MediaPlayer */

        mediaPlayer = MusicPlayer.getInstance().getMediaPlayer();


        playButton = (MaterialButton) findViewById(R.id.playButton);

        progressBar = findViewById(R.id.progressBar);

        handler = new Handler();


        /* Affichage du nom de l'échantillon */

        TextView nom = findViewById(R.id.nom);

        nom.setText(sample);
    }


    /* Options de la barre de Menus pour cette Activité */

    /* Home, Ajouter (+) , Supprimer (x) , Collection et Retour (<-) seulement */

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_detail, menu);


        MenuItem menuBack = menu.findItem(R.id.back);

        menuBack.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);


        MenuItem aboutUs = menu.findItem(R.id.about);
        SpannableString s = new SpannableString(aboutUs.getTitle());
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, s.length(), 0);
        aboutUs.setTitle(s);
        aboutUs.setVisible(false);

        return true;
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        SampleBDAdapter bdAdapter = new SampleBDAdapter(DetailsActivity.this);

        int option = item.getItemId();

        switch (option) {

            /* Retour à l'Activité MainActivity ( Home ) */

            case R.id.home:

                startActivity(new Intent(DetailsActivity.this, MainActivity.class));

                break;


            /* UPDATE TABLE Samples SET Collection = true WHERE Nom = <sample> */

            case R.id.ajouter:

                bdAdapter.updateCollection(sample, true);

                break;


            /* UPDATE TABLE Samples SET Collection = false WHERE Nom = <sample> */

            case R.id.supprimer:

                bdAdapter.updateCollection(sample, false);

                break;


            /* Redirige à l'Activité ListActivity et affiche la collection */

            case R.id.collection:

                Intent intent = new Intent(DetailsActivity.this, ListActivity.class);

                intent.putExtra("collection", true);

                startActivity(intent);

                break;


            case R.id.back:

               finish();

        }

        return super.onOptionsItemSelected(item);
    }


    private void setListeners() {


        /* Récupère la durée de l'échantillon pour la ProgressBar */

        mediaPlayer.setOnPreparedListener(mediaPlayer ->

                progressBar.setMax(mediaPlayer.getDuration()));
    }


    public void onPlayMusic(View view) {


        if (!isPaused && !mediaPlayer.isPlaying()) {


            /* Recherche de la Ressource de l'échantillon musical par son Nom */

            int resId = getResources()

                       .getIdentifier(sampleRootName, "raw", getPackageName());

            AssetFileDescriptor assetFileDescriptor = getResources().openRawResourceFd(resId);


            MainActivity.onStartMusic(mediaPlayer, assetFileDescriptor);


            /* Changement de l'icône Play ( |> ) à Pause ( || ) une fois la musique démarrée */

            playButton.setIcon(ContextCompat.getDrawable(this, android.R.drawable.ic_media_pause));

            updateProgressbar();


            mediaPlayer.setOnCompletionListener(mediaPlayer -> {

                mediaPlayer.reset();


            /* Changement de l'icône Pause à Play ( |> ) une fois la musique terminée */

            playButton.setIcon(ContextCompat.getDrawable(getApplicationContext(),

                    android.R.drawable.ic_media_play));
            });

        } else if (isPaused) {

            mediaPlayer.start();


            /* Changement de l'icône Play ( |> ) à Pause ( || ) une fois la musique redémarrée */

            playButton.setIcon(ContextCompat.getDrawable(this, android.R.drawable.ic_media_pause));

            isPaused = false;


        } else if (mediaPlayer.isPlaying()) {

            mediaPlayer.pause();


            /* Changement de l'icône Pause à Play une fois redémarré */

            playButton.setIcon(ContextCompat.getDrawable(this, android.R.drawable.ic_media_play));

            isPaused = true;
        }
    }


    private void updateProgressbar() {


        int currentPosition = mediaPlayer.getCurrentPosition();

        progressBar.setProgress(currentPosition);

        Runnable runnable = this::updateProgressbar;


        /* Update de la ProgessBar chaque milliseconde */

        handler.postDelayed(runnable, 1);
    }


    @Override
    protected void onPause() {


        super.onPause();

        mediaPlayer.reset();
    }

    /* Téléchargement de l'échantillon sur stockage interne du téléphone */

    @SuppressLint("MissingPermission")
    public void onDownload(View view) {


        int resId = getResources()

                .getIdentifier(sampleRootName, "raw", getPackageName());


        /* Appel à la méthode de lecture et écriture */

        Download.saveSample(this, resId, sample, categorie);
    }
}
