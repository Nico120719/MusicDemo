package com.example.musicdemotest;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.content.Intent;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

    }


    public void onAfficher(View view) {


        String buttonText = ((Button) view).getText().toString();

        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);

        intent.putExtra("instrument", buttonText);

        startActivity(intent);
    }
}
