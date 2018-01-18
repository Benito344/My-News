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
import com.behague.benjamin.mynews.Models.TopStories.TopStoriesMultimedia;
import com.behague.benjamin.mynews.Models.TopStories.TopStoriesResult;
import com.behague.benjamin.mynews.R;
import com.behague.benjamin.mynews.Utils.NYTStreams;
import com.behague.benjamin.mynews.Views.TopStoriesAdapter;

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

    private List<TopStoriesMain> topStoriesMain;
    private List<TopStoriesResult> topStoriesResults;
    private List<TopStoriesMultimedia> topStoriesMultimedia;

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
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    private void initRecyclerView(){
        this.topStoriesResults = new ArrayList<>();
        this.topStorieAdapter = new TopStoriesAdapter(this.topStoriesResults);
        this.recyclerView.setAdapter(this.topStorieAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void executeHttpRequest(){
        this.disposable = NYTStreams.streamTopStories().subscribeWith(new DisposableObserver<TopStoriesMain>() {

            @Override
            public void onNext(TopStoriesMain topStories){
                topStoriesResults.addAll(topStories.getResults());
                updateList(topStoriesResults);
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

    private void disposeWhenDestroy(){

        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    private void updateList(List<TopStoriesResult> topList){
        topStorieAdapter.notifyDataSetChanged();
    }
}


