package com.behague.benjamin.mynews.Models.SearchArticles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Benjamin BEHAGUE on 22/01/2018.
 */
public class SearchArticlesMain implements Serializable {

    @SerializedName("response")
    @Expose
    private SearchArticlesResponse response;

    public SearchArticlesResponse getResponse() {
        return response;
    }

    public void setResponse(SearchArticlesResponse response) {
        this.response = response;
    }

}
