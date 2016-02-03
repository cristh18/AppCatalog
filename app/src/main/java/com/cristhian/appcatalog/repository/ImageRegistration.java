package com.cristhian.appcatalog.repository;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.cristhian.appcatalog.entities.ImageEntity;
import com.cristhian.appcatalog.models.Entry;
import com.cristhian.appcatalog.models.ImImage;

/**
 * Created by ctolosa on 02/02/2016.
 */
public class ImageRegistration {

    /**
     *
     */
    private final static String LOG_TAG = ImageRegistration.class.getSimpleName();

    /**
     *
     */
    private static ImageRegistration imageRegistrationInstance = new ImageRegistration();

    /**
     *
     */
    private ImageRegistration() {
    }

    /**
     * @return
     */
    public static ImageRegistration getImageRegistrationInstance() {
        return imageRegistrationInstance;
    }


    AppRepository appRepository = AppRepository.getAppRepoInstance();

    /**
     *
     * @param context
     * @param entry
     */
    public void createImage(Context context, Entry entry) {

        ContentValues values = new ContentValues();
        String appId = appRepository.getAppByIdentifier(context, entry.getId().getAttributes().getImId());

        for (ImImage image : entry.getImImages()) {
            values.put(ImageEntity.APP_ID, appId);
            values.put(ImageEntity.LABEL, image.getLabel());
            values.put(ImageEntity.HEIGHT, image.getAttributes().getHeight());


            Uri uri = context.getContentResolver().insert(
                    ImageEntity.CONTENT_URI, values);
        }


    }
}
