package com.example.tanseer.deliciousfoodies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Supreme on 8/24/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "cuisines.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
// main recipe table
        final String SQL_CREATE_CUISINE_TABLE = "CREATE TABLE " + Contract.CuisineNameEntry.TABLE_NAME + " (" +
                Contract.CuisineNameEntry._ID + " INTEGER PRIMARY KEY," +
                Contract.CuisineNameEntry.COLUMN_CUISINE_NAME + " TEXT NOT NULL" +
                " )";

        db.execSQL(SQL_CREATE_CUISINE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Contract.CuisineNameEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
