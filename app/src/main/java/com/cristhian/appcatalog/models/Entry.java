package com.cristhian.appcatalog.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Cristhian on 2/1/2016.
 */
public class Entry {

    @Expose
    @SerializedName("im:name")
    ImName imName;

    @Expose
    @SerializedName("im:image")
    List<ImImage> imImages;

    @Expose
    @SerializedName("summary")
    Summary summary;

    @Expose
    @SerializedName("im:price")
    ImPrice imPrice;

    @Expose
    @SerializedName("im:contentType")
    ImContentType imContentType;

    @Expose
    @SerializedName("rights")
    Right rights;

    @Expose
    @SerializedName("title")
    Title title;

    @Expose
    @SerializedName("link")
    Link link;

    @Expose
    @SerializedName("id")
    Id id;

    @Expose
    @SerializedName("im:artist")
    ImArtist imArtist;

    @Expose
    @SerializedName("category")
    Category category;

    @Expose
    @SerializedName("im:releaseDate")
    ImReleaseDate imReleaseDate;


    public ImName getImName() {
        return imName;
    }

    public void setImName(ImName imName) {
        this.imName = imName;
    }

    public List<ImImage> getImImages() {
        return imImages;
    }

    public void setImImages(List<ImImage> imImages) {
        this.imImages = imImages;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public ImPrice getImPrice() {
        return imPrice;
    }

    public void setImPrice(ImPrice imPrice) {
        this.imPrice = imPrice;
    }

    public ImContentType getImContentType() {
        return imContentType;
    }

    public void setImContentType(ImContentType imContentType) {
        this.imContentType = imContentType;
    }

    public Right getRights() {
        return rights;
    }

    public void setRights(Right rights) {
        this.rights = rights;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public ImArtist getImArtist() {
        return imArtist;
    }

    public void setImArtist(ImArtist imArtist) {
        this.imArtist = imArtist;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ImReleaseDate getImReleaseDate() {
        return imReleaseDate;
    }

    public void setImReleaseDate(ImReleaseDate imReleaseDate) {
        this.imReleaseDate = imReleaseDate;
    }
}
