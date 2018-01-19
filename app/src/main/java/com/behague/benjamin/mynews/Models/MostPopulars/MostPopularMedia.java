package com.behague.benjamin.mynews.Models.MostPopulars;

import android.media.MediaMetadata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Benjamin BEHAGUE on 16/01/2018.
 */
public class MostPopularMedia {

    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("media-metadata")
    @Expose
    private List<MostPopularMediaMetadata> mediaMetadata = null;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<MostPopularMediaMetadata> getMediaMetadata() {
        return mediaMetadata;
    }

    public void setMediaMetadata(List<MostPopularMediaMetadata> mediaMetadata) {
        this.mediaMetadata = mediaMetadata;
    }

}
