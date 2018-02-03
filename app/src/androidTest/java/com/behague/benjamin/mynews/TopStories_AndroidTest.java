package com.behague.benjamin.mynews;

import android.support.test.runner.AndroidJUnit4;

import com.behague.benjamin.mynews.models.TopStories.TopStoriesMain;
import com.behague.benjamin.mynews.models.TopStories.TopStoriesResult;
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
public class TopStories_AndroidTest {

    @Test
    public void getUrl_Test () throws Exception{
        Observable<TopStoriesMain> observableResult = NYTStreams.streamTopStories();
        TestObserver<TopStoriesMain> testObserver = new TestObserver<>();

        observableResult.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        List<TopStoriesResult> mostResult = testObserver.values().get(0).getResults();

        assertNotNull(mostResult.get(0).getUrl());
    }
}
