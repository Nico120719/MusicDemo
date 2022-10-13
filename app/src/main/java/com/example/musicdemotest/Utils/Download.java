package com.example.musicdemotest.Utils;


import android.content.Context;

import android.os.Environment;

import android.widget.Toast;

import com.example.musicdemotest.R;
import com.example.musicdemotest.models.Sample;

import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Download {


    /* Méthode de téléchargement du fichier audio sur stockage interne du téléphone */

    /* ( ne fonctionne pas sur émulateur ) */

    public static void saveSample(Context context, int resId, String nom, String categorie)  {


        try {

            /* Recherche de la Ressource du fichier audio */

            InputStream inputStream = context.getResources().openRawResource(resId);


            /* Sélection du path menant au stockage interne du téléphone */

            File directory = new File(Environment.getExternalStorageDirectory()

                            .getAbsolutePath() + "/Download/Marco_&_Nico_Samples/"

                           + Sample.translateString(categorie));


            /* Création du dossier principal Marco_&_Nico_Samples */

            /* et du dossier associé à la catégorie si non-existants */

            if (!directory.exists()) directory.mkdir();

            File sample = new File(directory, nom);

            FileOutputStream fileOutputStream = new FileOutputStream(sample);

            byte[] buffer = new byte[1024];

            int length;


            /* Lecture et écriture du fichier */

            while((length = inputStream.read(buffer)) > 0) {

                fileOutputStream.write(buffer,0, length);
            }

            inputStream.close();

            fileOutputStream.flush();

            fileOutputStream.close();


            /* Affichage des messages de confirmation ou erreur */

            Toast.makeText(context, context.getString(R.string.downloadsuccessful), Toast.LENGTH_LONG).show();


        } catch (IOException e) {

            e.printStackTrace();

            Toast.makeText(context, context.getString(R.string.downloadfailed), Toast.LENGTH_LONG).show();
        }
    }
}
