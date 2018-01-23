package com.behague.benjamin.mynews.Controllers.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.behague.benjamin.mynews.Models.SearchArticles.SearchArticlesDoc;
import com.behague.benjamin.mynews.R;
import com.behague.benjamin.mynews.Views.SearchResultsAdapter;
import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultsActivity extends AppCompatActivity {

    private SearchResultsAdapter searchResultsAdapter;

    private List<SearchArticlesDoc> searchArticleDoc;

    @BindView(R.id.recycler_view_search)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);
        this.initRecyclerView();
        //searchArticleDoc = SearchActivity.searchArticlesDoc;
        //this.updateList();
    }

    public void initRecyclerView(){
        //this.searchArticleDoc = new ArrayList<>();
        this.searchResultsAdapter = new SearchResultsAdapter(SearchActivity.searchArticlesDoc, Glide.with(this));
        this.recyclerView.setAdapter(this.searchResultsAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(SearchResultsActivity.this));
    }

    public void updateList(){
        searchResultsAdapter.notifyDataSetChanged();
    }
}
