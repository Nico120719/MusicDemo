package com.example.musicdemotest.models;


import android.widget.ProgressBar;

import com.google.android.material.button.MaterialButton;


public class Sample {


    private String name;

    private String category;

    private String descriptionFR;

    private String descriptionEN;

    private String duree;

    private boolean collection;

    private ProgressBar progressBar;


    public Sample() {}


    public Sample(String name, String category, String descriptionFR, String descriptionEN,

                  String duree) {

        this.name = name;

        this.category = category;

        this.descriptionFR = descriptionFR;

        this.descriptionEN = descriptionEN;

        this.duree = duree;
    }


    public Sample(String name, String category, String descriptionFR, String descriptionEN,

                  String duree, boolean collection) {

        this.name = name;

        this.category = category;

        this.descriptionFR = descriptionFR;

        this.descriptionEN = descriptionEN;

        this.duree = duree;

        this.collection = collection;
    }


    public String getName() {

        return name;
    }


    public void setName(String name) {

        this.name = name;
    }


    public String getCategory() {

        return category;
    }


    public String getDescriptionFR() {

        return descriptionFR;
    }


    public String getDescriptionEN() {

        return descriptionEN;
    }


    public String getDuree() {

        return duree;
    }


    public Boolean isCollection() {

        return collection;
    }


    public void setCollection(boolean collection) {

        this.collection = collection;
    }


    public static String translateString(String categorie) {


        if (categorie.equalsIgnoreCase("cordes")) categorie = "strings";

        if (categorie.equalsIgnoreCase("vents")) categorie = "horns";

        if (categorie.equalsIgnoreCase("percussions")) categorie = "drums";

        if (categorie.equalsIgnoreCase("claviers")) categorie = "synths";

        if (categorie.equalsIgnoreCase("monde")) categorie = "world";

        return categorie;
    }
}
