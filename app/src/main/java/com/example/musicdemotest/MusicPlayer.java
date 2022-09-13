package com.example.musicdemotest;

import android.app.Application;

import android.media.MediaPlayer;


public class MusicPlayer extends Application {


    private static MediaPlayer instance;


    public static MediaPlayer getInstance() {

        if (instance == null) {

            synchronized (MediaPlayer.class) {

                if (instance == null) {

                    new MediaPlayer();
                }
            }
        }

        return instance;
    }
}

