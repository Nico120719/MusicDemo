package com.example.musicdemotest.database;


import android.content.Context;
import android.content.ContentValues;

import android.database.Cursor;
import android.database.DatabaseUtils;
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

                context);
    }


    public void openBD() {


        database = bdHelper.getWritableDatabase();
    }


    public void closeDB() {


        database.close();
    }


    public ArrayList<Sample> findSamples(String categorie) {


        openBD();

        String[] colonnes = { SampleDBHelper.NAME, SampleDBHelper.CATEGORY,

                SampleDBHelper.DESCRIPTION_FR, SampleDBHelper.DESCRIPTION_EN,

                SampleDBHelper.DUREE, SampleDBHelper.COLLECTION };

        Cursor cursor = null;

        String filtre;

        String[] args;

        if (categorie == null) {

            filtre = SampleDBHelper.COLLECTION + " = ?";

            args = new String[] { String.valueOf(1) };
        }

        else {

            filtre = SampleDBHelper.CATEGORY + " = ?";

            args = new String[] { categorie };
        }

        ArrayList<Sample> filteredSamples = new ArrayList<>();

        try  {

            cursor = database.query(SampleDBHelper.TABLE, colonnes, filtre, args,

                    null, null, null);

            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {

                Sample sample = new Sample(cursor.getString(0), cursor.getString(1),

                             cursor.getString(2), cursor.getString(3),

                             cursor.getString(4), cursor.getInt(5) != 0);

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


    public void updateCollection(String sampleName, boolean flag) {


        openBD();

        if (checkSampleStatus(sampleName, flag)) {

            int messageID = flag ? R.string.alreadyincollection : R.string.notincollection;

            Toast.makeText(context, context.getString(messageID), Toast.LENGTH_LONG).show();
        }

        else {

            ContentValues contentValues = new ContentValues();

            String filtre = SampleDBHelper.NAME + " = ?";

            String[] args = { sampleName };

            int messageID = flag ? R.string.addedtocollection : R.string.removedfromcollection;

            contentValues.put(SampleDBHelper.COLLECTION, flag);

            database.update(SampleDBHelper.TABLE, contentValues, filtre, args);

            Toast.makeText(context, context.getString(messageID), Toast.LENGTH_LONG).show();
        }

        closeDB();
    }


    public boolean checkSampleStatus(String sampleName, boolean flag)  {


        String selection = SampleDBHelper.NAME + " = ? AND " + SampleDBHelper.COLLECTION + " = ?";

        int sqlFlag = flag ? 1 : 0;

        String[] args = { sampleName, String.valueOf(sqlFlag)};

        return (DatabaseUtils.queryNumEntries(database, SampleDBHelper.TABLE, selection, args) > 0);
    }
}
