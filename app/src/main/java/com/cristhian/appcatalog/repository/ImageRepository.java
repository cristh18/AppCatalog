package com.cristhian.appcatalog.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cristhian.appcatalog.entities.AppEntity;
import com.cristhian.appcatalog.entities.ImageEntity;
import com.cristhian.appcatalog.helpers.AppsDBHelper;
import com.cristhian.appcatalog.models.AppImage;
import com.cristhian.appcatalog.models.Attribute;
import com.cristhian.appcatalog.models.ImImage;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * @param context
     * @return
     */
    public List<AppImage> getImagesByCategory(Context context, String categoryId, String height) {
        List<AppImage> appImages = new ArrayList<>();
        AppsDBHelper appsDBHelper = new AppsDBHelper(context);
        SQLiteDatabase db = appsDBHelper.getReadableDatabase();
        String query = "SELECT * FROM " + ImageEntity.TABLE_NAME + " i WHERE i." + ImageEntity.APP_ID +
                " IN(SELECT a." + AppEntity._ID + " FROM " + AppEntity.TABLE_NAME + " a WHERE a."
                + AppEntity.CATEGORY_ID + "=?) AND i." + ImageEntity.HEIGHT + "=?";

        Cursor cursor = db.rawQuery(query, new String[]{categoryId, height});

        if (cursor.moveToFirst()) {
            do {
                AppImage appImage = new AppImage();
                appImage.setImageIdentifier(cursor.getInt(cursor.getColumnIndex(ImageEntity._ID)));
                appImage.setImagerUrl(cursor.getString(cursor.getColumnIndex(ImageEntity.LABEL)));
                appImage.setImageHeight(cursor.getString(cursor.getColumnIndex(ImageEntity.HEIGHT)));
                appImage.setApplicationIdentifier(cursor.getInt(cursor.getColumnIndex(ImageEntity.APP_ID)));
                appImages.add(appImage);
            } while (cursor.moveToNext());
        }

        return appImages;
    }

}
