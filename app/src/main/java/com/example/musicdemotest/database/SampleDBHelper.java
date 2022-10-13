package com.example.musicdemotest.database;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class SampleDBHelper extends SQLiteOpenHelper {


    /* Déclaration du nom de la Database MusicSamples et des colonnes de la table Samples */

    public static final String DATABASE = "MusicSamples";

    public static final int VERSION = 1;


    public static final String TABLE = "Samples";

    public static final String ID = "_id";

    public static final String NAME = "Nom";

    public static final String CATEGORY = "Catégorie";

    public static final String DESCRIPTION_FR = "Description_FR";

    public static final String DESCRIPTION_EN = "Description_EN";

    public static final String DUREE = "Durée";

    public static final String COLLECTION = "Collection";


    /* Requête de création de la table Samples */

    public static final String DDL = "CREATE TABLE " + TABLE + " ("

            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "

            + NAME + " TEXT, " + CATEGORY + " TEXT, " + DESCRIPTION_FR + " TEXT, " + DESCRIPTION_EN + " TEXT, "

            + DUREE + " TEXT, " + COLLECTION + " BOOLEAN)";


    public SampleDBHelper(@Nullable Context context,

                          @Nullable String name,

                          @Nullable SQLiteDatabase.CursorFactory factory,

                          int version) {

        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /* Exécution de la requête de céation de la table Samples */

        sqLiteDatabase.execSQL(DDL);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}
}
