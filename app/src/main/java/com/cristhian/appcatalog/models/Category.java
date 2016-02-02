package com.cristhian.appcatalog.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristhian on 2/2/2016.
 */
public class Category {

    @Expose
    @SerializedName("attributes")
    CategoryAttribute attributes;

    public CategoryAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(CategoryAttribute attributes) {
        this.attributes = attributes;
    }
}
