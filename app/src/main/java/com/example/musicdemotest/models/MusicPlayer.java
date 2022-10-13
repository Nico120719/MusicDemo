package com.example.musicdemotest.models;

import android.app.Application;
import android.media.MediaPlayer;


/* Création d'un Singleton MediaPlayer dans la classe nommée MusicPlayer */

public class MusicPlayer extends Application {


    private final MediaPlayer mediaPlayer = new MediaPlayer();


    private static final class InstanceHolder {


        static final MusicPlayer instance = new MusicPlayer();
    }


    public static MusicPlayer getInstance() {


        return InstanceHolder.instance;
    }


    public MediaPlayer getMediaPlayer() {


        return mediaPlayer;
    }
}


