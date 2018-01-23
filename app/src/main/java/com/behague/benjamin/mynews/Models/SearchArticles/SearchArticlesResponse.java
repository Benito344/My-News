package com.behague.benjamin.mynews.Models.SearchArticles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Benjamin BEHAGUE on 22/01/2018.
 */
public class SearchArticlesResponse implements Serializable {

    @SerializedName("docs")
    @Expose
    private List<SearchArticlesDoc> docs = null;

    public List<SearchArticlesDoc> getDocs() {
        return docs;
    }

    public void setDocs(List<SearchArticlesDoc> docs) {
        this.docs = docs;
    }

}
