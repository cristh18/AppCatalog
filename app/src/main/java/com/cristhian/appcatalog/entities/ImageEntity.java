package com.cristhian.appcatalog.entities;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.cristhian.appcatalog.managers.DatabaseContract;

/**
 * Created by ctolosa on 02/02/2016.
 */
public class ImageEntity implements BaseColumns {

    public static final Uri CONTENT_URI =
            DatabaseContract.BASE_CONTENT_URI.buildUpon().appendPath(DatabaseContract.PATH_IMAGE).build();

    public static final String CONTENT_TYPE =
            ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + DatabaseContract.CONTENT_AUTHORITY + "/" + DatabaseContract.PATH_IMAGE;
    public static final String CONTENT_ITEM_TYPE =
            ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + DatabaseContract.CONTENT_AUTHORITY + "/" + DatabaseContract.PATH_IMAGE;

    public static final String TABLE_NAME = "image";

    public static final String APP_ID = "app_id";
    public static final String LABEL = "label";
    public static final String HEIGHT = "height";


    public static Uri buildImageUri(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }

    public static String getImageFromUri(Uri uri) {
        return uri.getPathSegments().get(1);
    }
}
