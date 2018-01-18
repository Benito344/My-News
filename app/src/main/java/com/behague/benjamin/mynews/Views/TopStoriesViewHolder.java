package com.behague.benjamin.mynews.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.behague.benjamin.mynews.Models.TopStories.TopStoriesResult;
import com.behague.benjamin.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Benjamin BEHAGUE on 18/01/2018.
 */

public class TopStoriesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.top_stories_item_title)
    TextView title;

    public TopStoriesViewHolder(View v){
        super(v);
        ButterKnife.bind(this, v);
    }

    public void updateDatas (TopStoriesResult topStories){
        this.title.setText(topStories.getTitle());
    }
}
