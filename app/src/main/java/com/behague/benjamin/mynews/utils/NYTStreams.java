package com.behague.benjamin.mynews.utils;


import com.behague.benjamin.mynews.models.MostPopulars.MostPopularMain;
import com.behague.benjamin.mynews.models.SearchArticles.SearchArticlesMain;
import com.behague.benjamin.mynews.models.Sports.SportsMain;
import com.behague.benjamin.mynews.models.TopStories.TopStoriesMain;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Benjamin BEHAGUE on 17/01/2018.
 */

//All methods in this class is for create streams
public class NYTStreams {

    public static Observable<TopStoriesMain> streamTopStories(){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);

        return nytService.getTopStories()
                .subscribeOn(Schedulers.io()) //Execute stream in thread Scheduler.io
                .observeOn(AndroidSchedulers.mainThread()) //Listen stream observable on main thread
                .timeout(5, TimeUnit.SECONDS); //Time for send data
    }

    public static Observable<MostPopularMain> streamMostPopular(){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);

        return nytService.getMostPopular()
                .subscribeOn(Schedulers.io()) //Execute stream in thread Scheduler.io
                .observeOn(AndroidSchedulers.mainThread()) //Listen stream observable on main thread
                .timeout(5,TimeUnit.SECONDS); //Time for send data
    }

    public static Observable<SportsMain> streamSports(){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);

        return nytService.getSports()
                .subscribeOn(Schedulers.io()) //Execute stream in thread Scheduler.io
                .observeOn(AndroidSchedulers.mainThread()) //Listen stream observable on main thread
                .timeout(5, TimeUnit.SECONDS); //Time for send data
    }

    public static Observable<SearchArticlesMain> streamSearchArticles(String term, String beginDate,
                                                                      String endDate, String section){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);

        return nytService.getSearchArticle(term, beginDate, endDate, section, true)
                .subscribeOn(Schedulers.io()) //Execute stream in thread Scheduler.io
                .observeOn(AndroidSchedulers.mainThread()) //Listen stream observable on main thread
                .timeout(5,TimeUnit.SECONDS); //Time for send data
    }

    public static Observable<SearchArticlesMain> streamSearchArticlesWhitoutDate(String term, String section){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);

        return nytService.getSearchArticleWhitoutDate(term, section, true)
                .subscribeOn(Schedulers.io()) //Execute stream in thread Scheduler.io
                .observeOn(AndroidSchedulers.mainThread()) //Listen stream observable on main thread
                .timeout(5,TimeUnit.SECONDS); //Time for send data
    }
}
