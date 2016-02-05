package com.cristhian.appcatalog.utils;

import android.content.Context;
import android.content.res.Configuration;

import com.cristhian.appcatalog.repository.AppRepository;
import com.cristhian.appcatalog.repository.CategoryRepository;
import com.cristhian.appcatalog.repository.ImageRepository;

/**
 * Created by Cristhian on 2/1/2016.
 */
public class Utilies {

    /**
     * @return
     */
    public static boolean validateCatalog(Context context) {
        boolean existCatalog = false;
        AppRepository appRepository = AppRepository.getAppRepoInstance();
        CategoryRepository categoryRepository = CategoryRepository.getCateRepoInstance();
        ImageRepository imageRepository = ImageRepository.getImageRepoInstance();

        Long countApps = appRepository.getCountApps(context);
        Long countCategories = categoryRepository.getCountCategories(context);
        Long countImages = imageRepository.getCountCategories(context);

//        if (countApps == 20 && countCategories == 9 && countImages == 60){
        if (countApps != 0 && countCategories != 0 && countImages != 0) {
            existCatalog = true;
        } else {
            existCatalog = false;
        }
        return existCatalog;
    }

    /**
     * @param context
     * @return
     */
    public static boolean isTablet(Context context) {
        return (context.getApplicationContext().getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

}
