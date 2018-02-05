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

    public static List<MostPopularResult> mostPopularList;
    private RequestManager glide;

    //Constructor
    public MostPopularAdapter(List<MostPopularResult> mostPopularResult, RequestManager glide){
        mostPopularList = mostPopularResult;
        this.glide = glide;
    }

    //Create view holder and inflating its xml layout
    @Override
    public MostPopularViewHolder onCreateViewHolder(ViewGroup parent, int viewTtype){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.recyclerview_item, parent, false);

        return new MostPopularViewHolder(v);
    }

    //Update user view
    @Override
    public void onBindViewHolder(MostPopularViewHolder viewHolder, int position){
        viewHolder.updateDatas(mostPopularList.get(position), this.glide);
    }

    //Return the total count of items in list
    @Override
    public int getItemCount(){
        return mostPopularList.size();
    }

    //Return URL of an article
    public static String getUrl (int position){
        return mostPopularList.get(position).getUrl();
    }
}
