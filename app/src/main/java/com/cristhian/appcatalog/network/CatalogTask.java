package com.cristhian.appcatalog.network;

import android.os.AsyncTask;
import android.util.Log;

import com.cristhian.appcatalog.interfaces.ICatalogSignature;
import com.cristhian.appcatalog.models.Catalog;
import com.cristhian.appcatalog.models.Feed;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Cristhian on 2/1/2016.
 */
public class CatalogTask extends AsyncTask<String, Void, Catalog> {

    private final String LOG_TAG = CatalogTask.class.getSimpleName();

    @Override
    protected Catalog doInBackground(String... params) {
        Catalog catalog = getCatalog(params[0]);
        return catalog;
    }

    /**
     * Get apps catalog
     *
     * @return
     */
    private Catalog getCatalog(String url) {
        Catalog catalog = null;
        try {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
            ICatalogSignature iCatalogSignature = retrofit.create(ICatalogSignature.class);
            Call<Catalog> call = iCatalogSignature.getCatalog();
            Response<Catalog> response = call.execute();
            Log.e("LOG", "Retrofit Response: " + response.raw().toString());

            if (response.body() != null) {
                catalog = response.body();
            }

        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
            Log.e(LOG_TAG, e.toString());
        }
        return catalog;
    }


    @Override
    protected void onPostExecute(Catalog catalog) {
        if (catalog != null){

        }
    }
}
