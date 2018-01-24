package com.behague.benjamin.mynews.Views.Sports;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.behague.benjamin.mynews.Models.Sports.SportsResult;
import com.behague.benjamin.mynews.Models.TopStories.TopStoriesResult;
import com.behague.benjamin.mynews.R;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Benjamin BEHAGUE on 18/01/2018.
 */

public class SportsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.top_stories_item_title)
    TextView title;
    @BindView(R.id.top_stories_item_section)
    TextView section;
    @BindView(R.id.top_stories_item_date)
    TextView date;
    @BindView(R.id.top_stories_item_image)
    ImageView image;


    public SportsViewHolder(View v){
        super(v);
        ButterKnife.bind(this, v);
    }

    public void updateDatas (SportsResult sportsResults, RequestManager glide){

        final int heightImage = 75;
        final int widthImage = 75;
        int sizeMultimedia = sportsResults.getMultimedia().size();

        if(sportsResults.getSubsection().isEmpty()){
            this.section.setText(sportsResults.getSection());
        } else {
            String section = sportsResults.getSection() + " > " + sportsResults.getSubsection();
            this.section.setText(section);
        }

        if(sizeMultimedia > 0){
                for (int i = 0 ; i<sizeMultimedia ; i++){
                        if(sportsResults.getMultimedia().get(i).getHeight().equals(heightImage) &&
                                sportsResults.getMultimedia().get(i).getWidth().equals(widthImage)){

                            glide.load(sportsResults.getMultimedia().get(0).getUrl())
                                    .apply(RequestOptions.centerCropTransform()).into(image);
                            image.setContentDescription(sportsResults.getMultimedia().get(0).getCaption());
                        }
                }
        }

        this.title.setText(sportsResults.getTitle());
        this.date.setText(dateTreatement(sportsResults.getPublishedDate()));
    }

    public String dateTreatement (String date){
        date = date.substring(0, 10);
        String[] dateSplit = date.split("-");
        String finalDate = dateSplit[2] + "/" + dateSplit[1] + "/" + dateSplit[0].substring(2, 4);

        return finalDate;
    }

}
