package com.behague.benjamin.mynews.models.TopStories;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopStoriesMain {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName("results")
    @Expose
    private List<TopStoriesResult> results = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public List<TopStoriesResult> getResults() {
        return results;
    }

    public void setResults(List<TopStoriesResult> topStoriesResults) {
        this.results = topStoriesResults;
    }

}

