package com.behague.benjamin.mynews.Utils;

import com.behague.benjamin.mynews.Models.TopStories.TopStoriesMain;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Benjamin BEHAGUE on 17/01/2018.
 */

public interface NYTService {

    /*@GET("svc/topstories/v2/home.json?api-key={API_KEY}")
    Observable<List<TopStoriesMain>> getTopStories(@Path("API_KEY") String api_key);*/

    @GET("svc/topstories/v2/home.json?api-key=f61e15b5379341758307c696363f35f9")
            Observable<TopStoriesMain> getTopStories();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
