package com.behague.benjamin.mynews.views.Sports;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.behague.benjamin.mynews.models.Sports.SportsResult;
import com.behague.benjamin.mynews.R;
import com.behague.benjamin.mynews.utils.DateTreatment;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Benjamin BEHAGUE on 18/01/2018.
 */

//This class is for model into a JAVA object the previous XML view created
public class SportsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.top_stories_item_title)
    TextView title;
    @BindView(R.id.top_stories_item_section)
    TextView section;
    @BindView(R.id.top_stories_item_date)
    TextView date;
    @BindView(R.id.top_stories_item_image)
    ImageView image;

    //Layout constructor
    public SportsViewHolder(View v){
        super(v);
        ButterKnife.bind(this, v);
    }

    //It for get elements of an article and set it UI
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
        this.date.setText(DateTreatment.DateTreatement(sportsResults.getPublishedDate()));
    }
}
