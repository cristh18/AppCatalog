package com.cristhian.appcatalog.models;

/**
 * Created by ctolosa on 04/02/2016.
 */
public class ApplicationData {

    private Integer applicationIdentifier;
    private String applicationTitle;
    private String applicationName;
    private AppImage applicationImage;
    private String applicationSummary;
    private String applicationReleaseDate;
    private Integer applicationCatId;

    public Integer getApplicationIdentifier() {
        return applicationIdentifier;
    }

    public void setApplicationIdentifier(Integer applicationIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
    }

    public String getApplicationTitle() {
        return applicationTitle;
    }

    public void setApplicationTitle(String applicationTitle) {
        this.applicationTitle = applicationTitle;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public AppImage getApplicationImage() {
        return applicationImage;
    }

    public void setApplicationImage(AppImage applicationImage) {
        this.applicationImage = applicationImage;
    }

    public String getApplicationSummary() {
        return applicationSummary;
    }

    public void setApplicationSummary(String applicationSummary) {
        this.applicationSummary = applicationSummary;
    }

    public String getApplicationReleaseDate() {
        return applicationReleaseDate;
    }

    public void setApplicationReleaseDate(String applicationReleaseDate) {
        this.applicationReleaseDate = applicationReleaseDate;
    }

    public Integer getApplicationCatId() {
        return applicationCatId;
    }

    public void setApplicationCatId(Integer applicationCatId) {
        this.applicationCatId = applicationCatId;
    }
}
