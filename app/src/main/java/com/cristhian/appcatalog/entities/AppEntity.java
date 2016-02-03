package com.cristhian.appcatalog.entities;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.cristhian.appcatalog.managers.DatabaseContract;

/**
 * Created by ctolosa on 02/02/2016.
 */
public class AppEntity implements BaseColumns {

    public static final Uri CONTENT_URI =
            DatabaseContract.BASE_CONTENT_URI.buildUpon().appendPath(DatabaseContract.PATH_APP).build();

    public static final String CONTENT_TYPE =
            ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + DatabaseContract.CONTENT_AUTHORITY + "/" + DatabaseContract.PATH_APP;
    public static final String CONTENT_ITEM_TYPE =
            ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + DatabaseContract.CONTENT_AUTHORITY + "/" + DatabaseContract.PATH_APP;

    public static final String TABLE_NAME = "app";

    public static final String CATEGORY_ID = "category_id";
    public static final String NAME = "name";
    public static final String SUMMARY = "summary";
    public static final String PRICE = "price";
    public static final String RIGHTS = "rights";
    public static final String TITLE = "title";
    public static final String LINK = "link";
    public static final String IDENTIFIER = "identifier";
    public static final String RELEASE_DATE = "release_date";

    public static Uri buildAppUri(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }

    public static Uri buildAppWithId() {
        return CONTENT_URI.buildUpon().appendPath("id").build();
    }


}
