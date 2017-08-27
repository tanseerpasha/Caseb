package com.example.tanseer.deliciousfoodies.data;

import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by Supreme on 8/24/2017.
 */

public class Provider extends ContentProvider {

    // The URI Matcher used by this content provider.
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private DbHelper mOpenHelper;

    static final int CUISINE = 100;
    static final int CUISINE_WITH_NAME = 101;

    // select individual cuisine
    private static final String sCuisineSelection = Contract.CuisineNameEntry.TABLE_NAME
            + "."
            + Contract.CuisineNameEntry.COLUMN_CUISINE_NAME
            + " = ? ";

    /*
        This UriMatcher will match each URI to the cuisine integer constants defined above.
     */
    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = Contract.CONTENT_AUTHORITY;

        matcher.addURI(authority, Contract.PATH_CUISINES,CUISINE);
        matcher.addURI(authority, Contract.PATH_CUISINES +"/*",CUISINE_WITH_NAME);
        return matcher;
    }

    /*
        create a new MoviesDbHelper for later use here.
     */
    @Override
    public boolean onCreate() {
        mOpenHelper = new DbHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case CUISINE:
                return Contract.CuisineNameEntry.CONTENT_TYPE;
            case CUISINE_WITH_NAME:
                return Contract.CuisineNameEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        //final int match = sUriMatcher.match(uri);
        Cursor retCursor;

        switch (sUriMatcher.match(uri)) {
            case CUISINE: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        Contract.CuisineNameEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case CUISINE_WITH_NAME: {
                retCursor = getCuisineByName(uri, projection, sortOrder);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);

        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    private Cursor getCuisineByName(Uri uri, String[] projection, String sortOrder) {
        String cuisineID = Contract.CuisineNameEntry.getCuisineIDFromUri(uri);
        String[] selectionArgs;
        String selection;
        selection = sCuisineSelection;
        selectionArgs = new String[]{cuisineID};

        return mOpenHelper.getReadableDatabase().query(
                Contract.CuisineNameEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }

    public Uri insert(@NonNull Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;
        switch (match) {
            case CUISINE: {
                long _id = db.insert(Contract.CuisineNameEntry.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = Contract.CuisineNameEntry.buildCuisineUri(_id);
                else
                    throw new SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        Log.d("TANNIMATCH","test" );
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);

        int rowsDeleted;
        // this makes delete all rows return the number of rows deleted
        if (null == selection) selection = "1";
        switch (match) {
            case CUISINE:
                rowsDeleted = db.delete(
                        Contract.CuisineNameEntry.TABLE_NAME, selection, selectionArgs);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        // Because a null deletes all rows
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }


    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;
        switch (match) {
            case CUISINE:
                rowsUpdated = db.update(Contract.CuisineNameEntry.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CUISINE:
                db.beginTransaction();
                int returnCuisineCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(Contract.CuisineNameEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCuisineCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCuisineCount;

            default:
                return super.bulkInsert(uri, values);
        }
    }

    // You do not need to call this method. This is a method specifically to assist the testing
    // framework in running smoothly. You can read more at:
    // http://developer.android.com/reference/android/content/ContentProvider.html#shutdown()
    @Override
    @TargetApi(11)
    public void shutdown() {
        mOpenHelper.close();
    }
}
