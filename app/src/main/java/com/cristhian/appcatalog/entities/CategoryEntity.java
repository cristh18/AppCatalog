package com.cristhian.appcatalog.entities;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.cristhian.appcatalog.managers.DatabaseContract;

/**
 * Created by ctolosa on 02/02/2016.
 */
public class CategoryEntity implements BaseColumns {

    public static final Uri CONTENT_URI =
            DatabaseContract.BASE_CONTENT_URI.buildUpon().appendPath(DatabaseContract.PATH_CATEGORY).build();

    public static final String CONTENT_TYPE =
            ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + DatabaseContract.CONTENT_AUTHORITY + "/" + DatabaseContract.PATH_CATEGORY;
    public static final String CONTENT_ITEM_TYPE =
            ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + DatabaseContract.CONTENT_AUTHORITY + "/" + DatabaseContract.PATH_CATEGORY;

    public static final String TABLE_NAME = "category";

    public static final String IDENTIFIER = "identifier";
    public static final String TERM = "term";
    public static final String LABEL = "label";

    public static Uri buildCategoryUri(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }

    public static String getCategoryFromUri(Uri uri) {
        return uri.getPathSegments().get(1);
    }

}
