package com.behague.benjamin.mynews.views.TopStories;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.behague.benjamin.mynews.models.TopStories.TopStoriesResult;
import com.behague.benjamin.mynews.R;
import com.bumptech.glide.RequestManager;

import java.util.List;

/**
 * Created by Benjamin BEHAGUE on 18/01/2018.
 */

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesViewHolder> {

    public static List<TopStoriesResult> topStoriesList;
    private RequestManager glide;

    //Constructor
    public TopStoriesAdapter(List<TopStoriesResult> topStoriesResults, RequestManager glide){
        topStoriesList = topStoriesResults;
        this.glide = glide;
    }

    //Create view holder and inflating its xml layout
    @Override
    public TopStoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new TopStoriesViewHolder(v);
    }

    //Update user view
    @Override
    public void onBindViewHolder(TopStoriesViewHolder viewHolder, int position){
        viewHolder.updateDatas(topStoriesList.get(position), this.glide);
    }

    //Return the total count of items in list
    @Override
    public int getItemCount(){
        return topStoriesList.size();
    }

    //Return URL of an article
    public static String getURL(int position){
        return topStoriesList.get(position).getUrl();
    }
}
