package com.example.musicdemotest.models;

import android.media.MediaPlayer;

import android.os.Parcel;
import android.os.Parcelable;


public class MyMediaPlayer extends MediaPlayer implements Parcelable {


    public static final Creator<MyMediaPlayer> CREATOR = new Creator<>() {

        @Override
        public MyMediaPlayer createFromParcel(Parcel in) {

            return new MyMediaPlayer();
        }


        @Override
        public MyMediaPlayer[] newArray(int size) {

            return new MyMediaPlayer[size];
        }
    };


    @Override
    public int describeContents() {

        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {}
}

