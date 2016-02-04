package com.cristhian.appcatalog.service;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.cristhian.appcatalog.entities.AppEntity;
import com.cristhian.appcatalog.entities.CategoryEntity;
import com.cristhian.appcatalog.entities.ImageEntity;
import com.cristhian.appcatalog.helpers.AppsDBHelper;
import com.cristhian.appcatalog.managers.DatabaseContract;

/**
 * Created by ctolosa on 02/02/2016.
 */
public class AppProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private AppsDBHelper appsDBHelper;

    static final int CATEGORY = 100;
    static final int APP = 200;
    static final int IMAGE = 300;
    static final int IMAGES_BY_APP_ID = 400;

    @Override
    public boolean onCreate() {
        appsDBHelper = new AppsDBHelper(getContext());
        return true;
    }

    static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = DatabaseContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, DatabaseContract.PATH_CATEGORY, CATEGORY);
        matcher.addURI(authority, DatabaseContract.PATH_APP, APP);
        matcher.addURI(authority, DatabaseContract.PATH_IMAGE, IMAGE);
        matcher.addURI(authority, DatabaseContract.PATH_IMAGE + "/*", IMAGES_BY_APP_ID);

        return matcher;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;

        switch (sUriMatcher.match(uri)) {
            case CATEGORY: {
                retCursor = appsDBHelper.getReadableDatabase().query(CategoryEntity.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }
            case APP: {
                retCursor = appsDBHelper.getReadableDatabase().query(AppEntity.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }

            case IMAGE: {
                retCursor = appsDBHelper.getReadableDatabase().query(ImageEntity.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }

            case IMAGES_BY_APP_ID: {
                retCursor = appsDBHelper.getReadableDatabase().query(
                        ImageEntity.TABLE_NAME,
                        projection, IMAGES_APP_ID, selectionArgs, null, null, sortOrder);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case CATEGORY:
                return CategoryEntity.CONTENT_TYPE;
            case APP:
                return AppEntity.CONTENT_TYPE;
            case IMAGE:
                return ImageEntity.CONTENT_TYPE;

            case IMAGES_BY_APP_ID:
                return ImageEntity.CONTENT_ITEM_TYPE;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = appsDBHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case CATEGORY: {
                long _id = db.insert(CategoryEntity.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = CategoryEntity.buildCategoryUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case APP: {
                long _id = db.insert(AppEntity.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = AppEntity.buildAppUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case IMAGE: {
                long _id = db.insert(ImageEntity.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = ImageEntity.buildImageUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        db.close();
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = appsDBHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;

        if (null == selection) selection = "1";
        switch (match) {
            case CATEGORY:
                rowsDeleted = db.delete(CategoryEntity.TABLE_NAME, selection, selectionArgs);
                break;
            case APP:
                rowsDeleted = db.delete(AppEntity.TABLE_NAME, selection, selectionArgs);
                break;
            case IMAGE:
                rowsDeleted = db.delete(ImageEntity.TABLE_NAME, selection, selectionArgs);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = appsDBHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;
        switch (match) {
            case CATEGORY:
                rowsUpdated = db.update(CategoryEntity.TABLE_NAME, values, selection, selectionArgs);
                break;
            case APP:
                rowsUpdated = db.update(AppEntity.TABLE_NAME, values, selection, selectionArgs);
                break;
            case IMAGE:
                rowsUpdated = db.update(ImageEntity.TABLE_NAME, values, selection, selectionArgs);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    /*----------*/

    private static final String IMAGES_APP_ID =
            ImageEntity._ID + " = ?";

}
