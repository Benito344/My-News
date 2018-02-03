package com.behague.benjamin.mynews;

import android.support.test.runner.AndroidJUnit4;

import com.behague.benjamin.mynews.models.SearchArticles.SearchArticlesDoc;
import com.behague.benjamin.mynews.models.SearchArticles.SearchArticlesMain;
import com.behague.benjamin.mynews.utils.NYTStreams;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Benjamin BEHAGUE on 01/02/2018.
 */

@RunWith(AndroidJUnit4.class)
public class Search_AndroidTest {

    @Test
    public void getUrlWithDate_Test () throws Exception{
        Observable<SearchArticlesMain> observableResult = NYTStreams.streamSearchArticles("Trump","20171101",
                                                                                            "20180131","Poilitics");
        TestObserver<SearchArticlesMain> testObserver = new TestObserver<>();

        observableResult.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        List<SearchArticlesDoc> searchResult = testObserver.values().get(0).getResponse().getDocs();

        assertNotNull(searchResult.get(0).getWebUrl());
    }

    @Test
    public void getUrlWithoutDate_Test() throws Exception{
        Observable<SearchArticlesMain> observableResult = NYTStreams.streamSearchArticlesWhitoutDate("Trump","Poilitics");
        TestObserver<SearchArticlesMain> testObserver = new TestObserver<>();

        observableResult.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        List<SearchArticlesDoc> searchResult = testObserver.values().get(0).getResponse().getDocs();

        assertNotNull(searchResult.get(0).getWebUrl());
    }
}
