package com.cristhian.appcatalog.interfaces;

import com.cristhian.appcatalog.models.Catalog;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Cristhian on 2/1/2016.
 */
public interface ICatalogSignature {

    @GET("us/rss/topfreeapplications/limit=20/json")
    Call<Catalog> getCatalog();

}
