package com.example.musicdemotest.models;


import com.example.musicdemotest.R;

import android.annotation.SuppressLint;
import android.content.Context;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import android.os.Build;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;


public class SampleAdapter extends ArrayAdapter<Sample> {


    public SampleAdapter(Context context, ArrayList<Sample> samples) {


        super(context, 0, samples);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View getView(int position, View view, ViewGroup parent) {


        if (view == null) view = LayoutInflater.from(getContext())

                                .inflate(R.layout.itemliste, parent, false);


        /* Recherche le Sample ( nom et catégorie ) */

        Sample sample = getItem(position);

        TextView nom = view.findViewById(R.id.txtNom);

        nom.setText(sample.getName());


        /* Recherche de l'icône appropriée à la catégorie du Sample */

        String categorie = Sample.translateString(sample.getCategory()) + "icon";

        int iconId = getContext().getResources()

                .getIdentifier(categorie, "drawable", getContext().getPackageName());

        Drawable icon = getContext().getResources().getDrawable(iconId);


        /* Ajout de padding */

        icon.setBounds(0, 0, 50, 60);

        nom.setCompoundDrawables(icon, null, null, null);


        /* Durée en secondes du Sample */

        TextView duree = view.findViewById(R.id.txtDuree);

        duree.setText(sample.getDuree());


        /* Affiche la description en anglais ou français, */

        /* en fonction des paramètres de configuration du téléphone */


        TextView description = view.findViewById(R.id.txtDescription);

        String language = getContext().getResources().getConfiguration().locale.getDisplayLanguage();

        if (language.equalsIgnoreCase("English")) description.setText(sample.getDescriptionEN());

        else description.setText(sample.getDescriptionFR());


        /* Couleur différente attribuée aux échantillons ne figurant pas dans la collection ( gris ) */

        /* et ceux de la collection ( bleu ) */

        int color;

        if (!sample.isCollection()) color = com.google.android.material.R.color.cardview_dark_background;

        else color = R.color.progressempty;


        GradientDrawable gradientDrawable = new GradientDrawable(

                GradientDrawable.Orientation.TOP_BOTTOM,

                new int[] { getContext().getResources()

                           .getColor(color,

                            getContext().getTheme()),

                            getContext().getResources().getColor(R.color.black)});

        gradientDrawable.setCornerRadius(20);

        view.setBackground(gradientDrawable);

        return view;
    }
}
