package com.behague.benjamin.mynews.views.Sports;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.behague.benjamin.mynews.models.Sports.SportsResult;
import com.behague.benjamin.mynews.R;
import com.bumptech.glide.RequestManager;

import java.util.List;

/**
 * Created by Benjamin BEHAGUE on 18/01/2018.
 */

public class SportsAdapter extends RecyclerView.Adapter<SportsViewHolder> {

    public static List<SportsResult> sportsList;
    private RequestManager glide;

    public SportsAdapter(List<SportsResult> sportsResults, RequestManager glide){
        sportsList = sportsResults;
        this.glide = glide;
    }

    @Override
    public SportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new SportsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SportsViewHolder viewHolder, int position){
        viewHolder.updateDatas(sportsList.get(position), this.glide);
    }

    @Override
    public int getItemCount(){
        return sportsList.size();
    }

    public static String getURL(int position){
        return sportsList.get(position).getUrl();
    }
}
