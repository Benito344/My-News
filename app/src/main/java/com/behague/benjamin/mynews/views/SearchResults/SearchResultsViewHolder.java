package com.behague.benjamin.mynews.views.SearchResults;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.behague.benjamin.mynews.models.SearchArticles.SearchArticlesDoc;
import com.behague.benjamin.mynews.R;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Benjamin BEHAGUE on 22/01/2018.
 */

public class SearchResultsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.top_stories_item_title)
    TextView title;
    @BindView(R.id.top_stories_item_section)
    TextView section;
    @BindView(R.id.top_stories_item_date)
    TextView date;
    @BindView(R.id.top_stories_item_image)
    ImageView image;


    public SearchResultsViewHolder(View v){
        super(v);
        ButterKnife.bind(this, v);
    }

    public void updateDatas (SearchArticlesDoc searchDocs, RequestManager glide){

        int sizeMultimedia = searchDocs.getMultimedia().size();

        if(searchDocs.getSubsectionName() == null){
            this.section.setText(searchDocs.getSectionName());
        } else {
            String section = searchDocs.getSectionName() + " > " + searchDocs.getSubsectionName();
            this.section.setText(section);
        }

        if(sizeMultimedia > 0){
            for (int i = 0 ; i<sizeMultimedia ; i++){
                if(searchDocs.getMultimedia().get(i).getSubtype().equals("thumbnail")){
                    glide.load("http://www.nytimes.com/"+searchDocs.getMultimedia().get(0).getUrl())
                            .apply(RequestOptions.centerCropTransform()).into(image);
                    image.setContentDescription(searchDocs.getMultimedia().get(0).getCaption());
                }
            }
        }

        this.title.setText(searchDocs.getHeadline().getMain());
        if(searchDocs.getPubDate() != null){
            this.date.setText(dateTreatement(searchDocs.getPubDate()));
        }
    }

    public String dateTreatement (String date){
        date = date.substring(0, 10);
        String[] dateSplit = date.split("-");
        String finalDate = dateSplit[2] + "/" + dateSplit[1] + "/" + dateSplit[0].substring(2, 4);

        return finalDate;
    }
}
