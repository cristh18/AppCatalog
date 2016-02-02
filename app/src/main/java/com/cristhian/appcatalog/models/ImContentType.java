package com.cristhian.appcatalog.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristhian on 2/1/2016.
 */
public class ImContentType {

    @Expose
    @SerializedName("attributes")
    ContentTypeAttribute attributes;

    public ContentTypeAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(ContentTypeAttribute attributes) {
        this.attributes = attributes;
    }
}
