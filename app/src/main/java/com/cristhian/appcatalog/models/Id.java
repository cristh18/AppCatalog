package com.cristhian.appcatalog.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristhian on 2/2/2016.
 */
public class Id {

    @Expose
    @SerializedName("label")
    String label;

    @Expose
    @SerializedName("attributes")
    IdAttribute attributes;


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public IdAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(IdAttribute attributes) {
        this.attributes = attributes;
    }
}
