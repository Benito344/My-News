package com.behague.benjamin.mynews.topStories_Test;

import com.behague.benjamin.mynews.models.TopStories.TopStoriesMain;
import com.behague.benjamin.mynews.models.TopStories.TopStoriesMultimedia;
import com.behague.benjamin.mynews.models.TopStories.TopStoriesResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Benjamin BEHAGUE on 01/02/2018.
 */

@RunWith(JUnit4.class)
public class TopStoriesObject_Test {

    private final Integer numberTest = 1993;
    private final String stringTest = "Test Value";
    private final List<TopStoriesResult> topStoriesResultsList = new ArrayList<>();
    private final List<TopStoriesMultimedia> topStoriesMultimediaList = new ArrayList<>();


    
    @Test
    public void TopStoriesMultimedia_Test() throws Exception{
        TopStoriesMultimedia topStoriesMultimedia = new TopStoriesMultimedia();
        
        topStoriesMultimedia.setUrl(stringTest);
        topStoriesMultimedia.setCaption(stringTest);
        topStoriesMultimedia.setHeight(numberTest);
        topStoriesMultimedia.setWidth(numberTest);

        topStoriesMultimediaList.add(topStoriesMultimedia);

        assertEquals(stringTest, topStoriesMultimedia.getUrl());
        assertEquals(stringTest, topStoriesMultimedia.getCaption());
        assertEquals(numberTest, topStoriesMultimedia.getHeight());
        assertEquals(numberTest, topStoriesMultimedia.getWidth());
    }

    @Test
    public void TopStoriesResult_Test() throws Exception{
        TopStoriesResult topStoriesResult = new TopStoriesResult();

        topStoriesResult.setAbstract(stringTest);
        topStoriesResult.setByline(stringTest);
        topStoriesResult.setPublishedDate(stringTest);
        topStoriesResult.setSection(stringTest);
        topStoriesResult.setSubsection(stringTest);
        topStoriesResult.setTitle(stringTest);
        topStoriesResult.setUrl(stringTest);
        topStoriesResult.setMultimedia(topStoriesMultimediaList);

        topStoriesResultsList.add(topStoriesResult);

        assertEquals(stringTest, topStoriesResult.getAbstract());
        assertEquals(stringTest, topStoriesResult.getByline());
        assertEquals(stringTest, topStoriesResult.getPublishedDate());
        assertEquals(stringTest, topStoriesResult.getSection());
        assertEquals(stringTest, topStoriesResult.getSubsection());
        assertEquals(stringTest, topStoriesResult.getTitle());
        assertEquals(stringTest, topStoriesResult.getUrl());
        assertEquals(topStoriesMultimediaList, topStoriesResult.getMultimedia());
    }

    @Test
    public void TopStoriesMain_Test() throws Exception{
        TopStoriesMain topStoriesMain = new TopStoriesMain();

        topStoriesMain.setStatus(stringTest);
        topStoriesMain.setNumResults(numberTest);
        topStoriesMain.setResults(topStoriesResultsList);

        assertEquals(stringTest, topStoriesMain.getStatus());
        assertEquals(numberTest, topStoriesMain.getNumResults());
        assertEquals(topStoriesResultsList, topStoriesMain.getResults());
    }
}
