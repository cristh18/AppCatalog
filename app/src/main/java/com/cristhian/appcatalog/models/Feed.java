package com.cristhian.appcatalog.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Cristhian on 2/1/2016.
 */
public class Feed {

    @Expose
    @SerializedName("author")
    Author author;

    @Expose
    @SerializedName("entry")
    List<Entry> entry;

    @Expose
    @SerializedName("updated")
    Update updated;

    @Expose
    @SerializedName("rights")
    Right rights;

    @Expose
    @SerializedName("title")
    Title title;

    @Expose
    @SerializedName("icon")
    Icon icon;

    @Expose
    @SerializedName("link")
    List<Link> link;

    @Expose
    @SerializedName("id")
    Id id;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

    public Update getUpdated() {
        return updated;
    }

    public void setUpdated(Update updated) {
        this.updated = updated;
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

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public List<Link> getLink() {
        return link;
    }

    public void setLink(List<Link> link) {
        this.link = link;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }
}
