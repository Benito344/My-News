package com.behague.benjamin.mynews.Utils;


import com.behague.benjamin.mynews.Models.MostPopulars.MostPopularMain;
import com.behague.benjamin.mynews.Models.SearchArticles.SearchArticlesMain;
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

    public static Observable<TopStoriesMain> streamTopStories(){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);

        return nytService.getTopStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(5, TimeUnit.SECONDS);
    }

    public static Observable<MostPopularMain> streamMostPopular(){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);

        return nytService.getMostPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(5,TimeUnit.SECONDS);
    }

    public static Observable<SearchArticlesMain> streamSearchArticles(String term, String beginDate,
                                                                      String endDate, String section){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);

        return nytService.getSearchArticle(term, beginDate, endDate, section, true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(500,TimeUnit.SECONDS);
    }

    public static Observable<SearchArticlesMain> streamSearchArticlesWhitoutDate(String term, String section){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);

        return nytService.getSearchArticleWhitoutDate(term, section, true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(500,TimeUnit.SECONDS);
    }
}
