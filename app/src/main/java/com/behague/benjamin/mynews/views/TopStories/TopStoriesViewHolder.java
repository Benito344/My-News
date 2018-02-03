package com.behague.benjamin.mynews.views.TopStories;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.behague.benjamin.mynews.models.TopStories.TopStoriesResult;
import com.behague.benjamin.mynews.R;
import com.behague.benjamin.mynews.utils.DateTreatment;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

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

        final int heightImage = 75;
        final int widthImage = 75;
        int sizeMultimedia = topStories.getMultimedia().size();

        if(topStories.getSubsection().isEmpty()){
            this.section.setText(topStories.getSection());
        } else {
            String section = topStories.getSection() + " > " + topStories.getSubsection();
            this.section.setText(section);
        }

        if(sizeMultimedia > 0){
                for (int i = 0 ; i<sizeMultimedia ; i++){
                        if(topStories.getMultimedia().get(i).getHeight().equals(heightImage) &&
                                topStories.getMultimedia().get(i).getWidth().equals(widthImage)){

                            glide.load(topStories.getMultimedia().get(0).getUrl())
                                    .apply(RequestOptions.centerCropTransform()).into(image);
                            image.setContentDescription(topStories.getMultimedia().get(0).getCaption());
                        }
                }
        }

        this.title.setText(topStories.getTitle());
        this.date.setText(DateTreatment.DateTreatement(topStories.getPublishedDate()));
    }
}
