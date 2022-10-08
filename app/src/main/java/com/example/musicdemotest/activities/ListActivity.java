package com.example.musicdemotest.activities;


import static com.example.musicdemotest.models.Sample.filterSamples;

import com.example.musicdemotest.R;
import com.example.musicdemotest.database.SampleBDAdapter;
import com.example.musicdemotest.models.Sample;
import com.example.musicdemotest.models.SampleAdapter;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;

import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity {


    private String categorie;

    private ListView listingView;

    private ArrayList<Sample> filteredSamples;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);

        setWidgets();

        setListeners();
    }


    private void setWidgets() {


        listingView = findViewById(R.id.listingView);

        categorie = getIntent().getStringExtra("categorie");

        filteredSamples = filterSamples(categorie);

        SampleBDAdapter bdAdapter = new SampleBDAdapter(ListActivity.this);

//        filteredSamples = bdAdapter.findAllSamplesByCategory(Sample.translateString(categorie).toLowerCase());

        SampleAdapter adapter = new SampleAdapter(this, filteredSamples);

        listingView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_detail, menu);

        MenuItem menuAdd = menu.findItem(R.id.ajouter);

        menuAdd.setVisible(false);

        MenuItem menuCollection = menu.findItem(R.id.collection);

        menuCollection.setVisible(false);

        return true;
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        finish();

        return super.onOptionsItemSelected(item);
    }



    private void setListeners() {


        listingView.setOnItemClickListener((adapterView, view, i, l) -> {

            AlphaAnimation alpha = new AlphaAnimation(1, 0.5f);

            alpha.setDuration(0);

            alpha.setFillAfter(true);

            view.startAnimation(alpha);

            Intent intent = new Intent(this, DetailsActivity.class);

            intent.putExtra("sample", filteredSamples.get(i).getName());

            intent.putExtra("categorie", categorie);

            startActivity(intent);
        });
    }
}
