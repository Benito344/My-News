package com.behague.benjamin.mynews.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.behague.benjamin.mynews.Models.TopStories.TopStoriesMultimedia;
import com.behague.benjamin.mynews.Models.TopStories.TopStoriesResult;
import com.behague.benjamin.mynews.R;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Benjamin BEHAGUE on 18/01/2018.
 */

public class TopStoriesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.top_stories_item_title)
    TextView title;
    @BindView(R.id.top_stories_item_section)
    TextView section;
    @BindView(R.id.top_stories_item_date)
    TextView date;
    @BindView(R.id.top_stories_item_image)
    ImageView image;


    public TopStoriesViewHolder(View v){
        super(v);
        ButterKnife.bind(this, v);
    }

    public void updateDatas (TopStoriesResult topStories, RequestManager glide){
        if(topStories.getSubsection().isEmpty()){
            this.section.setText(topStories.getSection());
        } else {
            String section = topStories.getSection() + " > " + topStories.getSubsection();
            this.section.setText(section);
        }
        this.title.setText(topStories.getTitle());
        this.date.setText(dateTreatement(topStories.getPublishedDate()));
        glide.load(topStories.getMultimedia().get(0).getUrl()).apply(RequestOptions.centerCropTransform()).into(image);
    }

    public String dateTreatement (String date){
        date = date.substring(0, 10);
        String[] dateSplit = date.split("-");
        String finalDate = dateSplit[2] + "/" + dateSplit[1] + "/" + dateSplit[0].substring(2, 4);

        return finalDate;
    }

}
