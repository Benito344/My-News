package com.behague.benjamin.mynews.topStories_Test;

import com.behague.benjamin.mynews.models.TopStories.TopStoriesResult;
import com.behague.benjamin.mynews.views.TopStories.TopStoriesAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Benjamin BEHAGUE on 03/02/2018.
 */

@RunWith(JUnit4.class)
public class TopStoriesAdapter_Test {

    private final String stringTest = "Test Value";
    private List<TopStoriesResult> topStoriesResultsList = new ArrayList<>();

    @Before
    public void SetUp() throws Exception {
        TopStoriesResult topStoriesResult = new TopStoriesResult();

        topStoriesResult.setAbstract(stringTest);
        topStoriesResult.setByline(stringTest);
        topStoriesResult.setPublishedDate(stringTest);
        topStoriesResult.setSection(stringTest);
        topStoriesResult.setSubsection(stringTest);
        topStoriesResult.setTitle(stringTest);
        topStoriesResult.setUrl(stringTest);

        topStoriesResultsList.add(topStoriesResult);

        TopStoriesAdapter.topStoriesList = new ArrayList<>();
        TopStoriesAdapter.topStoriesList.addAll(topStoriesResultsList);
    }

    @Test
    public void TopStoriesAdapterGetURL_Test() throws Exception{
        assertEquals(stringTest, TopStoriesAdapter.getURL(0));
    }
}
