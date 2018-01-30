package com.behague.benjamin.mynews.views.MostPopulars;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.behague.benjamin.mynews.models.MostPopulars.MostPopularResult;
import com.behague.benjamin.mynews.R;
import com.bumptech.glide.RequestManager;

import java.util.List;

/**
 * Created by Benjamin BEHAGUE on 18/01/2018.
 */

public class MostPopularAdapter extends RecyclerView.Adapter<MostPopularViewHolder>{

    private List<MostPopularResult> mostPopularList;
    private RequestManager glide;

    public MostPopularAdapter(List<MostPopularResult> mostPopularResult, RequestManager glide){
        this.mostPopularList = mostPopularResult;
        this.glide = glide;
    }

    @Override
    public MostPopularViewHolder onCreateViewHolder(ViewGroup parent, int viewTtype){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.recyclerview_item, parent, false);

        return new MostPopularViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MostPopularViewHolder viewHolder, int position){
        viewHolder.updateDatas(this.mostPopularList.get(position), this.glide);
    }

    @Override
    public int getItemCount(){
        return mostPopularList.size();
    }

    public String getUrl (int position){
        return this.mostPopularList.get(position).getUrl();
    }
}
