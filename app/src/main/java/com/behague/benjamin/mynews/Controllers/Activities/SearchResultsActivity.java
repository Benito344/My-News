package com.behague.benjamin.mynews.Controllers.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.behague.benjamin.mynews.R;
import com.behague.benjamin.mynews.Views.ItemClickRecyclerView;
import com.behague.benjamin.mynews.Views.SearchResultsAdapter;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultsActivity extends AppCompatActivity {

    private SearchResultsAdapter searchResultsAdapter;

    @BindView(R.id.recycler_view_search)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);
        this.initRecyclerView();
        this.configureOnClickRecyclerView();
    }

    public void initRecyclerView(){
        this.searchResultsAdapter = new SearchResultsAdapter(SearchActivity.searchArticlesDoc, Glide.with(this));
        this.recyclerView.setAdapter(this.searchResultsAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(SearchResultsActivity.this));
    }

    private void configureOnClickRecyclerView(){
        ItemClickRecyclerView.addTo(recyclerView, R.layout.fragment_top_stories)
                .setOnItemClickListener(new ItemClickRecyclerView.OnItemClickListener(){
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v){
                        String URL = searchResultsAdapter.getURL(position);
                        Intent webViewActivity = new Intent(SearchResultsActivity.this, WebViewActivity.class);
                        webViewActivity.putExtra("URL", URL);
                        startActivity(webViewActivity);
                    }
                });
    }

}
