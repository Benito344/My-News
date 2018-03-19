package com.behague.benjamin.mynews;

import android.support.test.runner.AndroidJUnit4;

import com.behague.benjamin.mynews.models.SearchArticles.SearchArticlesDoc;
import com.behague.benjamin.mynews.models.SearchArticles.SearchArticlesMain;
import com.behague.benjamin.mynews.utils.NYTStreams;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Benjamin BEHAGUE on 01/02/2018.
 */

@RunWith(AndroidJUnit4.class)
public class Search_AndroidTest {

    @Test
    public void getResultsWithDate_Test() throws Exception{
        Observable<SearchArticlesMain> observableResult = NYTStreams.streamSearchArticles("Trump","20171101",
                                                                                            "20180131","Poilitics");
        TestObserver<SearchArticlesMain> testObserver = new TestObserver<>();

        observableResult.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        List<SearchArticlesDoc> searchResult = testObserver.values().get(0).getResponse().getDocs();

        assertNotNull(searchResult);
    }

    @Test
    public void noResultsWithDate_Test() throws Exception{
        Observable<SearchArticlesMain> observableResult = NYTStreams.streamSearchArticles("fzfzfzfft","20171101",
                "20180131","Poilitics");
        TestObserver<SearchArticlesMain> testObserver = new TestObserver<>();

        observableResult.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        List<SearchArticlesDoc> searchResult;

        if(testObserver.values().get(0).getResponse().getDocs().size() == 0){
            searchResult = null;
        }
        else{
            searchResult = testObserver.values().get(0).getResponse().getDocs();
        }

        assertNull(searchResult);
    }

    @Test
    public void getResultsWithoutDate_Test() throws Exception{
        Observable<SearchArticlesMain> observableResult = NYTStreams.streamSearchArticlesWhitoutDate("Trump","Poilitics");
        TestObserver<SearchArticlesMain> testObserver = new TestObserver<>();

        observableResult.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        List<SearchArticlesDoc> searchResult = testObserver.values().get(0).getResponse().getDocs();

        assertNotNull(searchResult);
    }

    @Test
    public void noResultsWithoutDate_Test() throws Exception{
        Observable<SearchArticlesMain> observableResult = NYTStreams.streamSearchArticlesWhitoutDate("fzfzfzfft","Poilitics");
        TestObserver<SearchArticlesMain> testObserver = new TestObserver<>();

        observableResult.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        List<SearchArticlesDoc> searchResult;

        if(testObserver.values().get(0).getResponse().getDocs().size() == 0){
            searchResult = null;
        }
        else{
            searchResult = testObserver.values().get(0).getResponse().getDocs();
        }

        assertNull(searchResult);
    }
}
