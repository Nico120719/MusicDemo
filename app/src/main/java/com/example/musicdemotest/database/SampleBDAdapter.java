package com.example.musicdemotest.database;


import android.content.Context;
import android.content.ContentValues;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.widget.Toast;

import com.example.musicdemotest.R;
import com.example.musicdemotest.models.Sample;

import java.util.ArrayList;


public class SampleBDAdapter {


    private SQLiteDatabase database;

    private final SampleDBHelper bdHelper;

    private final Context context;


    public SampleBDAdapter(Context context) {


        this.context = context;

        this.bdHelper = new SampleDBHelper(

                context,

                SampleDBHelper.DATABASE,

                null,

                SampleDBHelper.VERSION);

        openBD();

        closeDB();
    }


    public void openBD() {


        database = bdHelper.getWritableDatabase();
    }


    public void closeDB() {


        database.close();
    }


    public ArrayList<Sample> findAllSamplesByCategory(String categorie) {


        openBD();

        String[] colonnes = { SampleDBHelper.NAME, SampleDBHelper.DESCRIPTION_FR,

                 SampleDBHelper.DESCRIPTION_EN, SampleDBHelper.DUREE };

        Cursor cursor = null;

        String filtre = SampleDBHelper.CATEGORY + " = ?";

        String[] args = { categorie };

        ArrayList<Sample> filteredSamples = new ArrayList<>();

        try  {

            cursor = database.query(SampleDBHelper.TABLE, colonnes, filtre, args,

                    null, null, null);

            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {

                Sample sample = new Sample(cursor.getString(0), cursor.getString(1),

                             cursor.getString(2), cursor.getString(3));

                filteredSamples.add(sample);

                cursor.moveToNext();
            }


        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }

        if (cursor != null) cursor.close();

        closeDB();

        return filteredSamples;
    }


    public void addSampleToCollection(String name) {


        updateCollection(name, true);
    }


    public void removeSampleFromCollection(String name) {


        updateCollection(name, false);
    }


    public void updateCollection(String sampleName, boolean flag) {


        openBD();

        ContentValues contentValues = new ContentValues();

        String filtre = SampleDBHelper.NAME + " = ?";

        String[] args = { sampleName };

        String message = String.valueOf(flag ? R.string.addedtocollection : R.string.removedfromcollection);

        contentValues.put(SampleDBHelper.COLLECTION, flag);

        database.update(SampleDBHelper.TABLE, contentValues, filtre, args);

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

        closeDB();
    }
}
