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
import com.behague.benjamin.mynews.models.MostPopulars.MostPopularMain;
import com.behague.benjamin.mynews.models.MostPopulars.MostPopularResult;
import com.behague.benjamin.mynews.R;
import com.behague.benjamin.mynews.utils.NYTStreams;
import com.behague.benjamin.mynews.views.ItemClickRecyclerView;
import com.behague.benjamin.mynews.views.MostPopulars.MostPopularAdapter;
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
public class MostPopularFragment extends Fragment {

    @BindView(R.id.recycler_view_most)
    RecyclerView recyclerView;

    private Disposable disposable;

    private MostPopularAdapter mostPopularAdapter;

    public List<MostPopularResult> mostPopularResults;

    public static MostPopularFragment newInstance() {
        return (new MostPopularFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_most_popular, container, false);
        ButterKnife.bind(this,view);
        this.initRecyclerView();
        this.executeHttpRequest();
        this.configureOnClickRecyclerView();
        return view;
    }

    //It for initialize RecyclerView
    private void initRecyclerView(){
        this.mostPopularResults = new ArrayList<>();
        this.mostPopularAdapter = new MostPopularAdapter(this.mostPopularResults, Glide.with(this));
        this.recyclerView.setAdapter(this.mostPopularAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    //It used for execute HTTP request and get results
    public void executeHttpRequest(){
        this.disposable = NYTStreams.streamMostPopular().subscribeWith(new DisposableObserver<MostPopularMain>(){

            @Override
            public void onNext(MostPopularMain mostPopular){
                mostPopularResults.addAll(mostPopular.getResults());
                updateList();
            }

            @Override
            public void onError(Throwable e){
                Log.e("TAG", e.getMessage());
            }

            @Override
            public void onComplete(){
                Log.e("TAG","Completed");
            }
        });
    }

    //It used when user clicked on item in recycler view
    private void configureOnClickRecyclerView(){
        ItemClickRecyclerView.addTo(recyclerView, R.layout.fragment_top_stories)
                .setOnItemClickListener(new ItemClickRecyclerView.OnItemClickListener(){
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v){
                        String URL = mostPopularAdapter.getUrl(position);
                        Intent webViewActivity = new Intent(getContext(), WebViewActivity.class);
                        webViewActivity.putExtra("URL", URL);
                        getContext().startActivity(webViewActivity);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    //Dispose subscription
    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    //It for notify RecyclerView when Http request return results
    private void updateList(){
        mostPopularAdapter.notifyDataSetChanged();
    }

}
