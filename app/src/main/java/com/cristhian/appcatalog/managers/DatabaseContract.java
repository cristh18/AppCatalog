package com.cristhian.appcatalog.managers;

import android.net.Uri;

/**
 *
 */
public class DatabaseContract{
    public static final String CONTENT_AUTHORITY = "com.cristhian.appcatalog";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_APP = "app";
    public static final String PATH_CATEGORY = "category";
    public static final String PATH_IMAGE = "image";

}

