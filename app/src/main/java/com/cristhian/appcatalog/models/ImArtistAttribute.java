package com.cristhian.appcatalog.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristhian on 2/2/2016.
 */
public class ImArtistAttribute {

    @Expose
    @SerializedName("href")
    String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
