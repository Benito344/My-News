package com.behague.benjamin.mynews.Utils;

import com.behague.benjamin.mynews.Models.MostPopulars.MostPopularMain;
import com.behague.benjamin.mynews.Models.SearchArticles.SearchArticlesMain;
import com.behague.benjamin.mynews.Models.Sports.SportsMain;
import com.behague.benjamin.mynews.Models.TopStories.TopStoriesMain;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Benjamin BEHAGUE on 17/01/2018.
 */

public interface NYTService {

    @GET("svc/topstories/v2/home.json?api-key=f61e15b5379341758307c696363f35f9")
            Observable<TopStoriesMain> getTopStories();

    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=f61e15b5379341758307c696363f35f9")
            Observable<MostPopularMain> getMostPopular();

    @GET("svc/topstories/v2/sports.json?api-key=f61e15b5379341758307c696363f35f9")
            Observable<SportsMain> getSports();

    @GET("svc/search/v2/articlesearch.json?api-key=f61e15b5379341758307c696363f35f9")
            Observable<SearchArticlesMain> getSearchArticle(@Query("q") String term, @Query("begin_date") String beginDate,
                                                            @Query("end_date") String endDate, @Query("fq=section_name:") String section,
                                                            @Query("facet_filter") boolean ff);
    @GET("svc/search/v2/articlesearch.json?api-key=f61e15b5379341758307c696363f35f9")

    Observable<SearchArticlesMain> getSearchArticleWhitoutDate(@Query("q") String term, @Query("fq=section_name:") String section,
                                                    @Query("facet_filter") boolean ff);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
