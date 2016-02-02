package com.cristhian.appcatalog.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristhian on 2/2/2016.
 */
public class Link {

    @Expose
    @SerializedName("attributes")
    LinkAttribute attributes;

    public LinkAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(LinkAttribute attributes) {
        this.attributes = attributes;
    }
}
