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

        Sample sample = getItem(position);

        TextView nom = view.findViewById(R.id.txtNom);

        String categorie = Sample.translateString(sample.getCategory()).toLowerCase() + "icon";

        int iconId = getContext().getResources()

                .getIdentifier(categorie, "drawable", getContext().getPackageName());

        Drawable icon = getContext().getResources().getDrawable(iconId);

        icon.setBounds(0, 0, 50, 60);

        nom.setCompoundDrawables(icon, null, null, null);

        TextView description = view.findViewById(R.id.txtDescription);

        TextView duree = view.findViewById(R.id.txtDuree);

        nom.setText(sample.getName());

        String language = getContext().getResources().getConfiguration().locale.getDisplayLanguage();

        if (language.equalsIgnoreCase("English")) description.setText(sample.getDescriptionEN());

        else description.setText(sample.getDescriptionFR());

        duree.setText(sample.getDuree());

        GradientDrawable gradientDrawable = new GradientDrawable(

                GradientDrawable.Orientation.TOP_BOTTOM,

                new int[] { getContext().getResources()

                           .getColor(com.google.android.material.R.color.cardview_dark_background,

                            getContext().getTheme()),

                            getContext().getResources().getColor(R.color.black)});

        gradientDrawable.setCornerRadius(20);

        view.setBackground(gradientDrawable);

        return view;
    }
}
