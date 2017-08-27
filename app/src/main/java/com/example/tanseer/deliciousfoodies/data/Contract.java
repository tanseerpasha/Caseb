package com.example.tanseer.deliciousfoodies.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Supreme on 8/24/2017.
 */

public class Contract {
    public static final String CONTENT_AUTHORITY = "com.example.tanseer.deliciousfoodies";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_CUISINES = "cuisine";
    public static final String PATH_DISHES = "dishes";

    public static final class CuisineNameEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_CUISINES).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +
                CONTENT_AUTHORITY + "/" + PATH_CUISINES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" +
                CONTENT_AUTHORITY + "/" + PATH_CUISINES;
        public static final String TABLE_NAME = "cuisine";


        public static final String COLUMN_CUISINE_NAME="name";


        public static final String[] CUISINE_NAME_COLUMNS = {
                _ID,
                COLUMN_CUISINE_NAME,

        };


        public static Uri buildCuisineUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildCuisineIDUri(String recipeID) {
            return CONTENT_URI.buildUpon().appendPath(recipeID).build();
        }

        public static String getCuisineIDFromUri(Uri uri){
            return uri.getPathSegments().get(1);
        }
    }
}
