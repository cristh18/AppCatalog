package com.cristhian.appcatalog.repository;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.cristhian.appcatalog.entities.CategoryEntity;
import com.cristhian.appcatalog.models.Category;

/**
 * Created by ctolosa on 02/02/2016.
 */
public class CategoryRegistration {

    /**
     *
     */
    private final static String LOG_TAG = CategoryRegistration.class.getSimpleName();

    /**
     *
     */
    private static CategoryRegistration categoryRegistrationInstance = new CategoryRegistration();

    /**
     *
     */
    private CategoryRegistration() {
    }

    /**
     * @return
     */
    public static CategoryRegistration getCatRegistrationInstance() {
        return categoryRegistrationInstance;
    }


    /**
     * @param context
     * @param category
     */
    public void createCategory(Context context, Category category) {

        ContentValues values = new ContentValues();
        values.put(CategoryEntity.IDENTIFIER, category.getAttributes().getImId());
        values.put(CategoryEntity.TERM, category.getAttributes().getTerm());
        values.put(CategoryEntity.LABEL, category.getAttributes().getLabel());

        Uri uri = context.getContentResolver().insert(
                CategoryEntity.CONTENT_URI, values);
    }
}
