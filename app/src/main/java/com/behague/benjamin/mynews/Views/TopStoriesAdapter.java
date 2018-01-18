package com.behague.benjamin.mynews.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.behague.benjamin.mynews.Models.TopStories.TopStoriesResult;
import com.behague.benjamin.mynews.R;
import com.bumptech.glide.RequestManager;

import java.util.List;

/**
 * Created by Benjamin BEHAGUE on 18/01/2018.
 */

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesViewHolder> {

    private List<TopStoriesResult> topStoriesList;
    private RequestManager glide;

    public TopStoriesAdapter(List<TopStoriesResult> topStoriesList, RequestManager glide){
        this.topStoriesList = topStoriesList;
        this.glide = glide;
    }

    @Override
    public TopStoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.top_stories_recyclerview_item, parent, false);
        return new TopStoriesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TopStoriesViewHolder viewHolder, int position){
        viewHolder.updateDatas(this.topStoriesList.get(position), this.glide);
    }

    @Override
    public int getItemCount(){
        return this.topStoriesList.size();
    }
}
