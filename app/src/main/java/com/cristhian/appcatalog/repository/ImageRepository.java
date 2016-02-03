package com.cristhian.appcatalog.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cristhian.appcatalog.entities.ImageEntity;
import com.cristhian.appcatalog.helpers.AppsDBHelper;

/**
 * Created by ctolosa on 03/02/2016.
 */
public class ImageRepository {

    private final static String LOG_TAG = ImageRepository.class.getSimpleName();

    private static ImageRepository imageRepoInstance = new ImageRepository();

    private ImageRepository() {

    }

    public static ImageRepository getImageRepoInstance() {
        return imageRepoInstance;
    }

    /**
     * @param context
     * @return
     */
    public Long getCountCategories(Context context) {
        Long count = null;
        AppsDBHelper appsDBHelper = new AppsDBHelper(context);
        SQLiteDatabase db = appsDBHelper.getReadableDatabase();

        String query = "SELECT count(i._id) FROM " + ImageEntity.TABLE_NAME + " i";

        Cursor cursor = db.rawQuery(query, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                count = cursor.getLong(0);
            } while (cursor.moveToNext());
        }

        return count;
    }

}
