package com.example.musicdemotest.database;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class SampleDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MusicSamples.db";
    public static final String DATABASE_PATH = "/data/data/com.example.musicdemotest/databases/";
    private static final int VERSION = 1;
    private final Context myContext;

//    public static final String DATABASE = "MusicSamples";
//
//    public static final int VERSION = 1;
//
//
    public static final String TABLE = "Samples";

    public static final String ID = "_id";

    public static final String NAME = "Nom";

    public static final String CATEGORY = "Catégorie";

    public static final String DESCRIPTION_FR = "Description_FR";

    public static final String DESCRIPTION_EN = "Description_EN";

    public static final String DUREE = "Durée";

    public static final String COLLECTION = "Collection";

//
//    public static final String DDL = "CREATE TABLE " + TABLE + " ("
//
//            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//
//            + NAME + " TEXT, " + CATEGORY + " TEXT, " + DESCRIPTION_FR + " TEXT, " + DESCRIPTION_EN + " TEXT, "
//
//            + DUREE + " TEXT, " + COLLECTION + " BOOLEAN)";


    public SampleDBHelper(Context context) {

        super(context, DATABASE_NAME, null, VERSION);
        this.myContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        boolean dbExist = checkDataBase();

        if(dbExist)
        {
            Log.v("DB Exists", "db exists");
            // By calling this method here onUpgrade will be called on a
            // writeable database, but only if the version number has been
            // bumped
            //onUpgrade(myDataBase, DATABASE_VERSION_old, DATABASE_VERSION);
        }

        boolean dbExist1 = checkDataBase();
        if(!dbExist1)
        {
            this.getReadableDatabase();
            try
            {
                this.close();
                copyDataBase();
            }
            catch (IOException e)
            {
                throw new Error("Error copying database");
            }
        }

    }

    private boolean checkDataBase()
    {
        boolean checkDB = false;
        try
        {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            File dbfile = new File(myPath);
            checkDB = dbfile.exists();
        }
        catch(SQLiteException e)
        {
        }
        return checkDB;
    }
    private void copyDataBase() throws IOException
    {

        InputStream mInput = myContext.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[2024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }


//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//
//
//        sqLiteDatabase.execSQL(DDL);
//    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}
}
