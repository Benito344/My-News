package com.behague.benjamin.mynews.Utils;


import com.behague.benjamin.mynews.Models.TopStories.TopStoriesMain;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Benjamin BEHAGUE on 17/01/2018.
 */

public class NYTStreams {

    private static final String API_KEY = "f61e15b5379341758307c696363f35f9";

    public static Observable<TopStoriesMain> streamTopStories(){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
/*        return nytService.getTopStories(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(5, TimeUnit.SECONDS);*/
        return nytService.getTopStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(5, TimeUnit.SECONDS);
    }

}
