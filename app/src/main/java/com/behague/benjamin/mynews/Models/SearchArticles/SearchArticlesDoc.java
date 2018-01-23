package com.behague.benjamin.mynews.Models.SearchArticles;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchArticlesDoc implements Serializable {

    @SerializedName("web_url")
    @Expose
    private String webUrl;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName("pub_date")
    @Expose
    private String pubDate;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("subsection_name")
    @Expose
    private String subsectionName;
    @SerializedName("headline")
    @Expose
    private SearchArticlesHeadline headline;
    @SerializedName("multimedia")
    @Expose
    private List<SearchArticlesMultimedia> multimedia = null;

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSubsectionName() {
        return subsectionName;
    }

    public void setSubsectionName(String subsectionName) {
        this.subsectionName = subsectionName;
    }

    public SearchArticlesHeadline getHeadline() {
        return headline;
    }

    public void setHeadline(SearchArticlesHeadline headline) {
        this.headline = headline;
    }

    public List<SearchArticlesMultimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<SearchArticlesMultimedia> multimedia) {
        this.multimedia = multimedia;
    }

}


