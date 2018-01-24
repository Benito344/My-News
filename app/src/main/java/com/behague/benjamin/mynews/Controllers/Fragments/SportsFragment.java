package com.behague.benjamin.mynews.Controllers.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.behague.benjamin.mynews.Controllers.Activities.WebViewActivity;
import com.behague.benjamin.mynews.Models.Sports.SportsMain;
import com.behague.benjamin.mynews.Models.Sports.SportsResult;
import com.behague.benjamin.mynews.R;
import com.behague.benjamin.mynews.Utils.NYTStreams;
import com.behague.benjamin.mynews.Views.ItemClickRecyclerView;
import com.behague.benjamin.mynews.Views.Sports.SportsAdapter;
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

    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    private void updateList(){
        sportsAdapter.notifyDataSetChanged();
    }

}
