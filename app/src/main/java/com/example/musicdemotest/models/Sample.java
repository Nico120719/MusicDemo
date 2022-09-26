package com.example.musicdemotest.models;


import android.widget.ProgressBar;

import com.google.android.material.button.MaterialButton;


public class Sample {


    private String title;

    private String category;

    private String descriptionFR;

    private String descriptionEN;

    private String duree;

    private MaterialButton button;

    private ProgressBar progressBar;

    private boolean collection;

    private String messageFR;

    private String messageEN;


    public Sample() {}


    public Sample(String title, String category, String descriptionFR, String descriptionEN,

                  String duree, boolean collection) {

        this.title = title;

        this.category = category;

        this.descriptionFR = descriptionFR;

        this.descriptionEN = descriptionEN;

        this.duree = duree;

        this.collection = collection;
    }


    public Sample(String title, String category, String descriptionFR, String descriptionEN,

                  String duree, MaterialButton button, boolean collection) {

        this.title = title;

        this.category = category;

        this.descriptionFR = descriptionFR;

        this.descriptionEN = descriptionEN;

        this.duree= duree;

        this.button = button;

        this.collection = collection;
    }


    public Sample(String title, String category, String descriptionFR, String descriptionEN,

                  String duree, MaterialButton button, ProgressBar progressBar, boolean collection) {

        this.title = title;

        this.category = category;

        this.descriptionFR = descriptionFR;

        this.descriptionEN = descriptionEN;

        this.duree = duree;

        this.button = button;

        this.progressBar = progressBar;

        this.collection = collection;
    }


    public String getTitle() {

        return title;
    }


    public void setTitle(String title) {

        this.title = title;
    }


    public String getCategory() {

        return category;
    }


    public void setCategory(String category) {

        this.category = category;
    }


    public String getDescriptionFR() {

        return descriptionFR;
    }


    public void setDescriptionFR(String descriptionFR) {

        this.descriptionFR = descriptionFR;
    }


    public String getDescriptionEN() {

        return descriptionEN;
    }


    public void setDescriptionEN(String descriptionEN) {

        this.descriptionEN = descriptionEN;
    }


    public String getDuree() {

        return duree;
    }


    public void setDuree(String duree) {

        this.duree = duree;
    }


    public MaterialButton getButton() {

        return button;
    }


    public void setButton(MaterialButton button) {

        this.button = button;
    }


    public ProgressBar getProgressBar() {

        return progressBar;
    }


    public void setProgressBar(ProgressBar progressBar) {

        this.progressBar = progressBar;
    }


    public boolean isCollection() {

        return collection;
    }


    public void setCollection(boolean collection) {

        this.collection = collection;
    }


    public String getMessageFR() {

        return messageFR;
    }


    public void setMessageFR(String message) {

        this.messageFR = message;
    }


    public String getMessageEN() {

        return messageEN;
    }


    public void setMessageEN(String message) {

        this.messageEN = message;
    }
}
