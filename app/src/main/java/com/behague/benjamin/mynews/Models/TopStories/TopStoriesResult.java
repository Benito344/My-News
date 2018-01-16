package com.behague.benjamin.mynews.Models.TopStories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Benjamin BEHAGUE on 16/01/2018.
 */
public class TopStoriesResult {

    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("subsection")
    @Expose
    private String subsection;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("byline")
    @Expose
    private String byline;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("des_facet")
    @Expose
    private List<Object> desFacet = null;
    @SerializedName("org_facet")
    @Expose
    private List<Object> orgFacet = null;
    @SerializedName("per_facet")
    @Expose
    private List<Object> perFacet = null;
    @SerializedName("geo_facet")
    @Expose
    private List<Object> geoFacet = null;
    @SerializedName("multimedia")
    @Expose
    private List<TopStoriesMultimedia> multimedia = null;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public List<Object> getDesFacet() {
        return desFacet;
    }

    public void setDesFacet(List<Object> desFacet) {
        this.desFacet = desFacet;
    }

    public List<Object> getOrgFacet() {
        return orgFacet;
    }

    public void setOrgFacet(List<Object> orgFacet) {
        this.orgFacet = orgFacet;
    }

    public List<Object> getPerFacet() {
        return perFacet;
    }

    public void setPerFacet(List<Object> perFacet) {
        this.perFacet = perFacet;
    }

    public List<Object> getGeoFacet() {
        return geoFacet;
    }

    public void setGeoFacet(List<Object> geoFacet) {
        this.geoFacet = geoFacet;
    }

    public List<TopStoriesMultimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<TopStoriesMultimedia> topStoriesMultimedia) {
        this.multimedia = topStoriesMultimedia;
    }

}
