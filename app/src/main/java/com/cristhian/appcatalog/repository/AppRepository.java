package com.cristhian.appcatalog.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cristhian.appcatalog.entities.AppEntity;
import com.cristhian.appcatalog.helpers.AppsDBHelper;
import com.cristhian.appcatalog.models.ApplicationData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ctolosa on 03/02/2016.
 */
public class AppRepository {

    private final static String LOG_TAG = AppRepository.class.getSimpleName();

    private static AppRepository appRepoInstance = new AppRepository();

    private AppRepository() {

    }

    public static AppRepository getAppRepoInstance() {
        return appRepoInstance;
    }

    /**
     *
     * @param context
     * @param appIdentifier
     * @return
     */
    public String getAppByIdentifier(Context context, String appIdentifier) {
        AppsDBHelper appsDBHelper = new AppsDBHelper(context);
        String appId = null;
        SQLiteDatabase db = appsDBHelper.getReadableDatabase();
        if (db == null) {
            appId = null;
        }

        String query = "SELECT * FROM " + AppEntity.TABLE_NAME + " where " + AppEntity.IDENTIFIER + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{appIdentifier});

        if (cursor.moveToFirst()) {
            do {
                appId = cursor.getString(cursor.getColumnIndex(AppEntity._ID));
            } while (cursor.moveToNext());
        }

        return appId;
    }

    /**
     *
     * @param context
     * @return
     */
    public Long getCountApps(Context context){
        Long count=null;
        AppsDBHelper appsDBHelper = new AppsDBHelper(context);
        SQLiteDatabase db = appsDBHelper.getReadableDatabase();

        String query = "SELECT count(a._id) FROM " + AppEntity.TABLE_NAME + " a";

        Cursor cursor = db.rawQuery(query, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                count = cursor.getLong(0);
            } while (cursor.moveToNext());
        }

        return count;
    }

    /**
     *
     * @param context
     * @param appId
     * @return
     */
    public ApplicationData getAppById(Context context, Integer appId) {
        ApplicationData applicationData = null;
        AppsDBHelper appsDBHelper = new AppsDBHelper(context);
        SQLiteDatabase db = appsDBHelper.getReadableDatabase();
        String query = "SELECT * FROM " + AppEntity.TABLE_NAME + " WHERE " + AppEntity._ID + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{appId.toString()});

        if (cursor.moveToFirst()) {
            do {
                applicationData = new ApplicationData();
                applicationData.setApplicationIdentifier(cursor.getInt(cursor.getColumnIndex(AppEntity._ID)));
                applicationData.setApplicationName(cursor.getString(cursor.getColumnIndex(AppEntity.NAME)));
                applicationData.setApplicationTitle(cursor.getString(cursor.getColumnIndex(AppEntity.TITLE)));
                applicationData.setApplicationSummary(cursor.getString(cursor.getColumnIndex(AppEntity.SUMMARY)));
                applicationData.setApplicationReleaseDate(cursor.getString(cursor.getColumnIndex(AppEntity.RELEASE_DATE)));
                applicationData.setApplicationCatId(cursor.getInt(cursor.getColumnIndex(AppEntity.CATEGORY_ID)));
            } while (cursor.moveToNext());
        }

        return applicationData;
    }
}
