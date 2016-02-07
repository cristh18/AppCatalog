package com.cristhian.appcatalog.utils;

import android.content.Context;
import android.content.res.Configuration;

import com.cristhian.appcatalog.models.CategoryAttribute;
import com.cristhian.appcatalog.repository.AppRepository;
import com.cristhian.appcatalog.repository.CategoryRepository;
import com.cristhian.appcatalog.repository.ImageRepository;

import java.util.List;

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

        if (countApps != 0 && countCategories != 0 && countImages != 0) {
            existCatalog = true;
        } else {
            existCatalog = false;
        }
        return existCatalog;
    }

    public static String searchCategoryName(Context context, int category) {
        String categoryName = "";
        CategoryRepository categoryRepository = CategoryRepository.getCateRepoInstance();
        List<CategoryAttribute> categories = categoryRepository.getCategories(context);
        for (CategoryAttribute c : categories) {
            if ((category+1) == Integer.parseInt(c.getImId())) {
                categoryName = c.getLabel();
                break;
            }
        }
        return categoryName;
    }

    public static String getShortAppName(String largeAppName){
        String shortAppName = "";
        String[] appName = largeAppName.split("-");
        shortAppName = appName[0];
        return shortAppName;
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
