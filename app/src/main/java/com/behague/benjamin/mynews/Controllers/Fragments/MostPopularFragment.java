package com.behague.benjamin.mynews.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.behague.benjamin.mynews.Models.MostPopulars.MostPopularMain;
import com.behague.benjamin.mynews.Models.MostPopulars.MostPopularResult;
import com.behague.benjamin.mynews.R;
import com.behague.benjamin.mynews.Utils.NYTStreams;
import com.behague.benjamin.mynews.Views.MostPopularAdapter;
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
    RecyclerView RecyclerView;

    private Disposable disposable;

    private MostPopularAdapter mostPopularAdapter;

    private List<MostPopularResult> mostPopularResults;

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
        return view;
    }

    private void initRecyclerView(){
        this.mostPopularResults = new ArrayList<>();
        this.mostPopularAdapter = new MostPopularAdapter(this.mostPopularResults, Glide.with(this));
        this.RecyclerView.setAdapter(this.mostPopularAdapter);
        this.RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void executeHttpRequest(){
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    private void updateList(){
        mostPopularAdapter.notifyDataSetChanged();
    }

}
