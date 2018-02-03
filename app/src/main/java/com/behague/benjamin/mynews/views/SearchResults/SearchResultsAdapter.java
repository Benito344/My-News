package com.behague.benjamin.mynews.views.SearchResults;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.behague.benjamin.mynews.models.SearchArticles.SearchArticlesDoc;
import com.behague.benjamin.mynews.R;
import com.bumptech.glide.RequestManager;

import java.util.List;

/**
 * Created by Benjamin BEHAGUE on 22/01/2018.
 */

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsViewHolder> {

    public static List<SearchArticlesDoc> searchArticlesDocs;
    private RequestManager glide;

    public SearchResultsAdapter(List<SearchArticlesDoc> searchDocs, RequestManager glide) {
        searchArticlesDocs = searchDocs;
        this.glide = glide;
    }

    @Override
    public SearchResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new SearchResultsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SearchResultsViewHolder viewHolder, int position) {
        viewHolder.updateDatas(searchArticlesDocs.get(position), this.glide);
    }

    @Override
    public int getItemCount() {
        return searchArticlesDocs.size();
    }

    public static String getURL(int position) {
        return searchArticlesDocs.get(position).getWebUrl();
    }

}
