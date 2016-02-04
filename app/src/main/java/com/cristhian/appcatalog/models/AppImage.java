package com.cristhian.appcatalog.models;

/**
 * Created by ctolosa on 04/02/2016.
 */
public class AppImage {

    private Integer imageIdentifier;
    private String imagerUrl;
    private String imageHeight;
    private Integer applicationIdentifier;

    public Integer getImageIdentifier() {
        return imageIdentifier;
    }

    public void setImageIdentifier(Integer imageIdentifier) {
        this.imageIdentifier = imageIdentifier;
    }

    public String getImagerUrl() {
        return imagerUrl;
    }

    public void setImagerUrl(String imagerUrl) {
        this.imagerUrl = imagerUrl;
    }

    public String getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(String imageHeight) {
        this.imageHeight = imageHeight;
    }

    public Integer getApplicationIdentifier() {
        return applicationIdentifier;
    }

    public void setApplicationIdentifier(Integer applicationIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
    }
}
