package com.cristhian.appcatalog.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.cristhian.appcatalog.interfaces.ICatalogResponse;
import com.cristhian.appcatalog.interfaces.ICatalogSignature;
import com.cristhian.appcatalog.models.Catalog;
import com.cristhian.appcatalog.models.Entry;
import com.cristhian.appcatalog.repository.AppRegistration;
import com.cristhian.appcatalog.repository.ImageRegistration;

import java.util.List;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Cristhian on 2/1/2016.
 */
public class CatalogTask extends AsyncTask<Object, Void, Catalog> {

    private final String LOG_TAG = CatalogTask.class.getSimpleName();

    private ICatalogResponse iCatalogResponse;

    private static final int SPLASH_TIME = 3000;

    boolean existCatalog;

    AppRegistration appRegistration = AppRegistration.getAppRegistrationInstance();
    ImageRegistration imageRegistration = ImageRegistration.getImageRegistrationInstance();

    Context context;

    public CatalogTask(Context context, ICatalogResponse catalogResponse) {
        this.iCatalogResponse = catalogResponse;
        this.context = context;
    }

    @Override
    protected Catalog doInBackground(Object... params) {
        try {
            Thread.sleep(SPLASH_TIME);
            Catalog catalog = getCatalog((String)params[0], (Boolean)params[1], (Boolean)params[2]);
            return catalog;
        } catch (InterruptedException e) {
            Log.e(LOG_TAG, e.getMessage());
            return null;
        }
    }

    /**
     * Get apps catalog
     *
     * @return
     */
    private Catalog getCatalog(String url, boolean executeService, boolean validCatalog) {
        Catalog catalog = null;
        existCatalog = false;

        if (executeService && !validCatalog){
            try {
                Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
                ICatalogSignature iCatalogSignature = retrofit.create(ICatalogSignature.class);
                Call<Catalog> call = iCatalogSignature.getCatalog();
                Response<Catalog> response = call.execute();
                Log.d("LOG", "Retrofit Response: " + response.raw().toString());

                if (response.body() != null) {
                    catalog = response.body();
                    existCatalog = true;
                }

            } catch (Exception e) {
                Log.e(LOG_TAG, e.getMessage());
                Log.e(LOG_TAG, e.toString());
            }
        }else if (!executeService && validCatalog){
            existCatalog = true;
        }

        return catalog;
    }

    /**
     *
     * @param apps
     */
    private boolean saveCatalog(List<Entry> apps){
        boolean catalogSaved = false;

        try {
            for (Entry app:apps) {
                appRegistration.createApp(context, app);
                imageRegistration.createImage(context, app);
            }
            catalogSaved = true;
        }catch (Exception e){
            Log.e(LOG_TAG, e.getMessage());
            catalogSaved = false;
        }
        return catalogSaved;
    }

    @Override
    protected void onPostExecute(Catalog catalog) {
        if (catalog != null && existCatalog) {
            if (saveCatalog(catalog.getFeed().getEntry())){
                iCatalogResponse.responseCatalog(true);
            }else {
                iCatalogResponse.responseCatalog(false);
            }
        }else if (catalog==null && existCatalog){
            iCatalogResponse.responseCatalog(true);
        }else {
            iCatalogResponse.responseCatalog(false);
        }
    }
}
