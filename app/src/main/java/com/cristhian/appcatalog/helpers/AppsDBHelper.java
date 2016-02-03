package com.cristhian.appcatalog.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cristhian.appcatalog.entities.AppEntity;
import com.cristhian.appcatalog.entities.CategoryEntity;
import com.cristhian.appcatalog.entities.ImageEntity;

/**
 * Created by ctolosa on 02/02/2016.
 */
public class AppsDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "app_catalog.db";

    public AppsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_CATEGORY_TABLE = " CREATE TABLE " + CategoryEntity.TABLE_NAME + " (" +
                CategoryEntity._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CategoryEntity.IDENTIFIER + " INTEGER UNIQUE NOT NULL, " +
                CategoryEntity.TERM + " TEXT NOT NULL, " +
                CategoryEntity.LABEL + " TEXT NOT NULL " +
                " );";

        final String SQL_CREATE_APP_TABLE = " CREATE TABLE " + AppEntity.TABLE_NAME + " (" +
                AppEntity._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                AppEntity.NAME + " TEXT NOT NULL, " +
                AppEntity.SUMMARY + " TEXT NOT NULL, " +
                AppEntity.PRICE + " TEXT NOT NULL, " +
                AppEntity.RIGHTS + " TEXT NOT NULL, " +
                AppEntity.TITLE + " TEXT NOT NULL, " +
                AppEntity.LINK + " TEXT NOT NULL, " +
                AppEntity.IDENTIFIER + " INTEGER NOT NULL, " +
                AppEntity.RELEASE_DATE + " TEXT NOT NULL, " +
                AppEntity.CATEGORY_ID + " INTEGER NOT NULL, " +
                " FOREIGN KEY (" + AppEntity.CATEGORY_ID + ") REFERENCES " + CategoryEntity.TABLE_NAME + " (" + CategoryEntity._ID + ")" +
                " );";


        final String SQL_CREATE_IMAGE_TABLE = " CREATE TABLE " + ImageEntity.TABLE_NAME + " (" +
                ImageEntity._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ImageEntity.LABEL + " TEXT NOT NULL, " +
                ImageEntity.HEIGHT + " INTEGER NOT NULL, " +
                ImageEntity.APP_ID + " INTEGER NOT NULL, " +
                " FOREIGN KEY (" + ImageEntity.APP_ID + ") REFERENCES " + AppEntity.TABLE_NAME + " (" + AppEntity._ID + ")" +
                " );";

        db.execSQL(SQL_CREATE_CATEGORY_TABLE);
        db.execSQL(SQL_CREATE_APP_TABLE);
        db.execSQL(SQL_CREATE_IMAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoryEntity.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AppEntity.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ImageEntity.TABLE_NAME);

        onCreate(db);
    }
}