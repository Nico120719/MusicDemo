package com.example.musicdemotest.models;

import android.app.Application;


public class MusicPlayer extends Application {


    private final MyMediaPlayer mediaPlayer = new MyMediaPlayer();


    private static final class InstanceHolder {

        static final MusicPlayer instance = new MusicPlayer();
    }


    public static MusicPlayer getInstance() {

        return InstanceHolder.instance;
    }


    public MyMediaPlayer getMediaPlayer() {

        return mediaPlayer;
    }
}


