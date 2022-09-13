package com.example.musicdemotest;


import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

import android.os.Parcelable;
import android.util.TypedValue;
import android.view.View;

import android.widget.Button;

import android.content.Intent;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    private MediaPlayer mediaPlayer = MusicPlayer.getInstance();

    private Button stringsButton, hornsButton, drumsButton, synthsButton, worldButton;

    private final int MAX_TEXT_SIZE = 34;

    private final int MIN_TEXT_SIZE = 24;

    private final int THRESHOLD = 7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        List<Button> buttons = List.of(

                findViewById(R.id.stringsButton),

                findViewById(R.id.hornsButton),

                findViewById(R.id.drumsButton),

                findViewById(R.id.synthsButton),

                findViewById(R.id.worldButton));
        

        int textSize = MAX_TEXT_SIZE;

        for (Button button : buttons) if (button.getText().toString().length() > THRESHOLD) textSize = MIN_TEXT_SIZE;

        for (Button button : buttons) button.setTextSize(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM,textSize);

    }


    public void onAfficher(View view) {


        String buttonText = ((Button) view).getText().toString();

        Bundle bundle = new Bundle();

        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);

        bundle.putString("instrument", buttonText);

        bundle.putParcelable("mediaplayer", (Parcelable) mediaPlayer);

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
