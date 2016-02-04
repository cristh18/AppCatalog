package com.cristhian.appcatalog.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cristhian.appcatalog.entities.CategoryEntity;
import com.cristhian.appcatalog.helpers.AppsDBHelper;
import com.cristhian.appcatalog.models.CategoryAttribute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ctolosa on 02/02/2016.
 */
public class CategoryRepository {

    private final static String LOG_TAG = CategoryRepository.class.getSimpleName();

    private static CategoryRepository categoryRepoInstance = new CategoryRepository();

    private CategoryRepository() {

    }

    public static CategoryRepository getCateRepoInstance() {
        return categoryRepoInstance;
    }

    /**
     * @param context
     * @param categoryIdentifier
     * @return
     */
    public String getCategoryByIdentifier(Context context, String categoryIdentifier) {
        AppsDBHelper appsDBHelper = new AppsDBHelper(context);
        String categoryId = null;
        SQLiteDatabase db = appsDBHelper.getReadableDatabase();
        if (db == null) {
            categoryId = null;
        }

        String query = "SELECT * FROM " + CategoryEntity.TABLE_NAME + " where " + CategoryEntity.IDENTIFIER + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{categoryIdentifier});

        if (cursor.moveToFirst()) {
            do {
                categoryId = cursor.getString(cursor.getColumnIndex(CategoryEntity._ID));
            } while (cursor.moveToNext());
        }

        return categoryId;
    }

    /**
     * @param context
     * @return
     */
    public Long getCountCategories(Context context) {
        Long count = null;
        AppsDBHelper appsDBHelper = new AppsDBHelper(context);
        SQLiteDatabase db = appsDBHelper.getReadableDatabase();

        String query = "SELECT count(c._id) FROM " + CategoryEntity.TABLE_NAME + " c";

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
    public List<CategoryAttribute> getCategories(Context context) {
        List<CategoryAttribute> categoryAttributes = new ArrayList<>();
        AppsDBHelper appsDBHelper = new AppsDBHelper(context);
        SQLiteDatabase db = appsDBHelper.getReadableDatabase();

        String query = "SELECT * FROM " + CategoryEntity.TABLE_NAME + " c";

        Cursor cursor = db.rawQuery(query, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                CategoryAttribute categoryAttribute = new CategoryAttribute();
                categoryAttribute.setImId(cursor.getString(cursor.getColumnIndex(CategoryEntity._ID)));
                categoryAttribute.setLabel(cursor.getString(cursor.getColumnIndex(CategoryEntity.LABEL)));
                categoryAttributes.add(categoryAttribute);
            } while (cursor.moveToNext());
        }

        return categoryAttributes;
    }
}
