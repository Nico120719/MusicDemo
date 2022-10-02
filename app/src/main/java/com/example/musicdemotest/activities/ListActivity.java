package com.example.musicdemotest.activities;

import com.example.musicdemotest.R;
import com.example.musicdemotest.models.Sample;
import com.example.musicdemotest.models.SampleAdapter;

import static com.example.musicdemotest.models.Sample.filterSamples;

import android.content.Intent;

import android.os.Bundle;

import android.widget.ListView;

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

        SampleAdapter adapter = new SampleAdapter(this, filteredSamples);

        listingView.setAdapter(adapter);
    }


    private void setListeners() {


        listingView.setOnItemClickListener((adapterView, view, i, l) -> {

            Intent intent = new Intent(this, DetailsActivity.class);

            intent.putExtra("sample", filteredSamples.get(i).getName());

            intent.putExtra("categorie", categorie);

            startActivity(intent);
        });
    }
}
