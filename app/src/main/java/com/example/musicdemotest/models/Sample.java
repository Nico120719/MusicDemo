package com.example.musicdemotest.models;


/* Modèle et attributs de l'échantillon */

public class Sample {


    private String name;

    private String category;

    private String descriptionFR;

    private String descriptionEN;

    private String duree;

    private boolean collection;


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


    /* Convertit le nom de la catégorie si la configuration du langage est en français */

    public static String translateString(String categorie) {


        if (categorie.equalsIgnoreCase("cordes")) categorie = "strings";

        if (categorie.equalsIgnoreCase("vents")) categorie = "horns";

        if (categorie.equalsIgnoreCase("percussions")) categorie = "drums";

        if (categorie.equalsIgnoreCase("claviers")) categorie = "synths";

        if (categorie.equalsIgnoreCase("monde")) categorie = "world";

        return categorie;
    }
}
