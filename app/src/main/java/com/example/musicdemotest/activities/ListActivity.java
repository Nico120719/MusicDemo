package com.example.musicdemotest.activities;


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
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity {


    private ListView listingView;

    private ArrayList<Sample> filteredSamples;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);

        setWidgets();

        setListeners();
    }


    @Override
    protected void onRestart() {


        super.onRestart();

        setContentView(R.layout.activity_list);

        setWidgets();

        setListeners();
    }


    private void setWidgets() {


        listingView = findViewById(R.id.listingView);

        String categorie = getIntent().getStringExtra("categorie");

        SampleBDAdapter bdAdapter = new SampleBDAdapter(ListActivity.this);

        if (typeListe().equals("collection"))  {

            filteredSamples = bdAdapter.findSamples(null);

            TextView titre = findViewById(R.id.instructions);

            titre.setText(R.string.collection);

            titre.setLetterSpacing(0.2f);

            titre.setTextSize(24);
        }

        else  filteredSamples = bdAdapter.findSamples(categorie);

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

        MenuItem menuDelete = menu.findItem(R.id.supprimer);

        menuDelete.setVisible(false);

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

                startActivity(new Intent(ListActivity.this, MainActivity.class));

                break;


            case R.id.collection:

                getIntent().putExtra("revalidate", true);

                recreate();

                break;


            case R.id.back:

                finish();
        }

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

            intent.putExtra("categorie", filteredSamples.get(i).getCategory());

            startActivity(intent);
        });
    }


    public String typeListe() {


        boolean collection = getIntent().getBooleanExtra("collection", false);

        boolean revalidate = getIntent().getBooleanExtra("revalidate", false);

        String type;

        if (collection || revalidate) type = "collection";

        else type = "catégories";

        return type;
    }
}
