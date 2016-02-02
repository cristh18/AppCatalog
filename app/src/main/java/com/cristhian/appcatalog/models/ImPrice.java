package com.cristhian.appcatalog.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristhian on 2/1/2016.
 */
public class ImPrice {

    @Expose
    @SerializedName("label")
    String label;

    @Expose
    @SerializedName("attributes")
    PriceAttribute attributes;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public PriceAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(PriceAttribute attributes) {
        this.attributes = attributes;
    }
}
