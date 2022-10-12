package com.example.musicdemotest.activities;


import com.example.musicdemotest.R;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;


public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about_us);

        setWidgets();
    }


    private void setWidgets() {


        TextView thankYou = findViewById(R.id.thanks);

        thankYou.setText(R.string.thanks);


    }


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

            case R.id.home:

                startActivity(new Intent(AboutUsActivity.this, MainActivity.class));

                break;


            case R.id.back:

                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
