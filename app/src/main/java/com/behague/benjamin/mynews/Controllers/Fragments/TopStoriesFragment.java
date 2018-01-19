package com.behague.benjamin.mynews.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.behague.benjamin.mynews.Models.TopStories.TopStoriesMain;
import com.behague.benjamin.mynews.Models.TopStories.TopStoriesResult;
import com.behague.benjamin.mynews.R;
import com.behague.benjamin.mynews.Utils.NYTStreams;
import com.behague.benjamin.mynews.Views.ItemClickRecyclerView;
import com.behague.benjamin.mynews.Views.TopStoriesAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private Disposable disposable;

    private TopStoriesAdapter topStorieAdapter;

    private List<TopStoriesResult> topStoriesResults;

    public static TopStoriesFragment newInstance() {
        return (new TopStoriesFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_stories, container, false);
        ButterKnife.bind(this, view);
        this.initRecyclerView();
        this.executeHttpRequest();
        this.configureOnClickRecyclerView();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    private void initRecyclerView(){
        this.topStoriesResults = new ArrayList<>();
        this.topStorieAdapter = new TopStoriesAdapter(this.topStoriesResults, Glide.with(this));
        this.recyclerView.setAdapter(this.topStorieAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void executeHttpRequest(){
        this.disposable = NYTStreams.streamTopStories().subscribeWith(new DisposableObserver<TopStoriesMain>() {

            @Override
            public void onNext(TopStoriesMain topStories){
                topStoriesResults.addAll(topStories.getResults());
                updateList();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e("TAG", "Completed");
            }
        });
    }

    private void configureOnClickRecyclerView(){
        ItemClickRecyclerView.addTo(recyclerView, R.layout.fragment_top_stories)
                .setOnItemClickListener(new ItemClickRecyclerView.OnItemClickListener(){
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v){
                        String URL = topStorieAdapter.getURL(position);
                        Log.e("TAG", URL);
                    }
                });
    }

    private void disposeWhenDestroy(){

        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    private void updateList(){
        topStorieAdapter.notifyDataSetChanged();
    }
}


