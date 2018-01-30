package com.behague.benjamin.mynews.models.SearchArticles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Benjamin BEHAGUE on 22/01/2018.
 */

public class SearchArticlesHeadline implements Serializable {

    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("kicker")
    @Expose
    private String kicker;

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

}
