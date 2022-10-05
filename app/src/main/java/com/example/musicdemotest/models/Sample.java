package com.example.musicdemotest.models;


import android.widget.ProgressBar;

import com.example.musicdemotest.activities.ListActivity;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;


public class Sample {


    private String name;

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


    public Sample(String name, String category, String descriptionFR, String descriptionEN,

                  String duree, boolean collection) {

        this.name = name;

        this.category = category;

        this.descriptionFR = descriptionFR;

        this.descriptionEN = descriptionEN;

        this.duree = duree;

        this.collection = collection;
    }


    public Sample(String name, String category, String descriptionFR, String descriptionEN,

                  String duree, MaterialButton button, boolean collection) {

        this.name = name;

        this.category = category;

        this.descriptionFR = descriptionFR;

        this.descriptionEN = descriptionEN;

        this.duree= duree;

        this.button = button;

        this.collection = collection;
    }


    public Sample(String name, String category, String descriptionFR, String descriptionEN,

                  String duree, MaterialButton button, ProgressBar progressBar, boolean collection) {

        this.name = name;

        this.category = category;

        this.descriptionFR = descriptionFR;

        this.descriptionEN = descriptionEN;

        this.duree = duree;

        this.button = button;

        this.progressBar = progressBar;

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


    public static ArrayList<Sample> filterSamples(String categorie) {


        ArrayList<Sample> filteredSamples = new ArrayList<>();

        for (Sample sample : getSamples()) {

            if (sample.getCategory().equalsIgnoreCase(translateString(categorie)))

                filteredSamples.add(sample);
        }

        return filteredSamples;
    }


    public static String translateString(String categorie) {


        if (categorie.equalsIgnoreCase("cordes")) categorie = "strings";

        if (categorie.equalsIgnoreCase("vents")) categorie = "horns";

        if (categorie.equalsIgnoreCase("percussions")) categorie = "drums";

        if (categorie.equalsIgnoreCase("claviers")) categorie = "synths";

        if (categorie.equalsIgnoreCase("monde")) categorie = "world";

        return categorie;
    }


    public static ArrayList<Sample> getSamples() {


        return new ArrayList<>(List.of(

                new Sample("drums.wav",

                        "Drums",

                        "Échantillon de percussion lent style Trip Hop",

                        "Slow Trip Hop drum sample",

                        "0:15",

                        false),

                new Sample("industrialdrums.mp3",

                        "Drums",

                        "Échantillon de percussion industrielle",

                        "Industrial drum sample",

                        "0:11",

                        false),

                new Sample("fastdrumsnare.wav",

                        "Drums",

                        "Percussion Jazz tempo rapide",

                        "Fast tempo Jazz drum sample",

                        "0:02",

                        false),

                new Sample("horns.wav",

                        "Horns",

                        "Échantillon de trompette Jazz lent",

                        "Slow Jazz trumpet sample",

                        "0:13",

                        false),

                new Sample("strings.wav",

                        "Strings",

                        "Grattement de guitare accoustique",

                        "Accoustic guitar strum sample",

                        "0:06",

                        false),

                new Sample("synths.wav",

                        "Synths",

                        "Clavier électonique Pop",

                        "Pop electonic synth sample",

                        "0:09",

                        false),

                new Sample("world.wav",

                        "World",

                        "Échantllon koto oriental",

                        "Oriental koto sample",

                        "0:09",

                        false),

                new Sample("cheer.wav",

                        "Vocal",

                        "Foule en délire",

                        "Crowd cheering",

                        "0:04",

                        false),

                new Sample("scream.wav",

                        "Vocal",

                        "Cri de peur",

                        "Fearful scream",

                        "0:01",

                        false)
        ));
    }


}
