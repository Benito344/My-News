package com.behague.benjamin.mynews.views.MostPopulars;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.behague.benjamin.mynews.models.MostPopulars.MostPopularResult;
import com.behague.benjamin.mynews.R;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Benjamin BEHAGUE on 18/01/2018.
 */

public class MostPopularViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.top_stories_item_title)
    TextView title;
    @BindView(R.id.top_stories_item_section)
    TextView section;
    @BindView(R.id.top_stories_item_date)
    TextView date;
    @BindView(R.id.top_stories_item_image)
    ImageView image;

    public MostPopularViewHolder(View v){
        super(v);
        ButterKnife.bind(this, v);
    }

    public void updateDatas(MostPopularResult mostPopular, RequestManager glide){

        int sizeMediaMetaData = mostPopular.getMedia().get(0).getMediaMetadata().size();
        final int heightImage = 75;
        final int widthImage = 75;

        this.section.setText(mostPopular.getSection());
        this.title.setText(mostPopular.getTitle());
        this.date.setText(dateTreatement(mostPopular.getPublishedDate()));

        if(sizeMediaMetaData>0){
            for(int i =0; i<sizeMediaMetaData; i++){
                if(mostPopular.getMedia().get(0).getMediaMetadata().get(i).getHeight().equals(heightImage) &&
                        mostPopular.getMedia().get(0).getMediaMetadata().get(i).getWidth().equals(widthImage)){

                    glide.load(mostPopular.getMedia().get(0).getMediaMetadata().get(i).getUrl())
                            .apply(RequestOptions.centerCropTransform()).into(image);
                    image.setContentDescription(mostPopular.getMedia().get(0).getCaption());
                }
            }
        }
    }

    private String dateTreatement (String date){
        date = date.substring(0, 10);
        String[] dateSplit = date.split("-");
        String finalDate = dateSplit[2] + "/" + dateSplit[1] + "/" + dateSplit[0].substring(2, 4);

        return finalDate;
    }
}
