package com.behague.benjamin.mynews;

import android.support.test.runner.AndroidJUnit4;

import com.behague.benjamin.mynews.models.Sports.SportsMain;
import com.behague.benjamin.mynews.models.Sports.SportsResult;
import com.behague.benjamin.mynews.utils.NYTStreams;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Benjamin BEHAGUE on 03/02/2018.
 */

@RunWith(AndroidJUnit4.class)
public class Sports_AndroidTest {
    @Test
    public void getUrl_Test () throws Exception{
        Observable<SportsMain> observableResult = NYTStreams.streamSports();
        TestObserver<SportsMain> testObserver = new TestObserver<>();

        observableResult.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        List<SportsResult> mostResult = testObserver.values().get(0).getResults();

        assertNotNull(mostResult.get(0).getUrl());
    }
}
