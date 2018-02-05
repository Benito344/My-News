package com.behague.benjamin.mynews.controllers.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.behague.benjamin.mynews.controllers.activities.WebViewActivity;
import com.behague.benjamin.mynews.models.Sports.SportsMain;
import com.behague.benjamin.mynews.models.Sports.SportsResult;
import com.behague.benjamin.mynews.R;
import com.behague.benjamin.mynews.utils.NYTStreams;
import com.behague.benjamin.mynews.views.ItemClickRecyclerView;
import com.behague.benjamin.mynews.views.Sports.SportsAdapter;
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
public class SportsFragment extends Fragment {

    @BindView(R.id.recycler_view_sports)
    RecyclerView recyclerView;

    private Disposable disposable;

    private SportsAdapter sportsAdapter;

    private List<SportsResult> sportsResults;

    public static SportsFragment newInstance() {
        return (new SportsFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sports, container, false);
        ButterKnife.bind(this,view);
        this.initRecyclerView();
        this.executeHttpRequest();
        this.configureOnClickRecyclerView();
        return view;
    }

    //It for initialize RecyclerView
    private void initRecyclerView(){
        this.sportsResults = new ArrayList<>();
        this.sportsAdapter = new SportsAdapter(this.sportsResults, Glide.with(this));
        this.recyclerView.setAdapter(this.sportsAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    //It used for execute HTTP request and get results
    private void executeHttpRequest(){
        this.disposable = NYTStreams.streamSports().subscribeWith(new DisposableObserver<SportsMain>() {

            @Override
            public void onNext(SportsMain sports){
                sportsResults.addAll(sports.getResults());
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

    //It used when user clicked on item in recycler view
    private void configureOnClickRecyclerView(){
        ItemClickRecyclerView.addTo(recyclerView, R.layout.fragment_top_stories)
                .setOnItemClickListener(new ItemClickRecyclerView.OnItemClickListener(){
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v){
                        String URL = sportsAdapter.getURL(position);
                        Intent webViewActivity = new Intent(getContext(), WebViewActivity.class);
                        webViewActivity.putExtra("URL", URL);
                        getContext().startActivity(webViewActivity);
                    }
                });
    }

    //Dispose subscription
    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    //It for notify RecyclerView when Http request return results
    private void updateList(){
        sportsAdapter.notifyDataSetChanged();
    }

}
