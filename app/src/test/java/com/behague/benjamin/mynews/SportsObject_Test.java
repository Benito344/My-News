package com.behague.benjamin.mynews;

import com.behague.benjamin.mynews.models.Sports.SportsMain;
import com.behague.benjamin.mynews.models.Sports.SportsMultimedia;
import com.behague.benjamin.mynews.models.Sports.SportsResult;

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
public class SportsObject_Test {

    private final Integer numberTest = 1993;
    private final String stringTest = "Test Value";
    private List<SportsMultimedia> sportsMultimediaList = new ArrayList<>();
    private List<SportsResult> sportsResultsList = new ArrayList<>();

    @Test
    public void SportsMultimedia_Test() throws Exception{
        SportsMultimedia sportsMultimedia = new SportsMultimedia();

        sportsMultimedia.setCaption(stringTest);
        sportsMultimedia.setUrl(stringTest);
        sportsMultimedia.setHeight(numberTest);
        sportsMultimedia.setWidth(numberTest);

        sportsMultimediaList.add(sportsMultimedia);

        assertEquals(stringTest, sportsMultimedia.getCaption());
        assertEquals(stringTest, sportsMultimedia.getUrl());
        assertEquals(numberTest, sportsMultimedia.getHeight());
        assertEquals(numberTest, sportsMultimedia.getWidth());
    }

    @Test
    public void SportsResult_Test() throws Exception{
        SportsResult sportsResult = new SportsResult();

        sportsResult.setAbstract(stringTest);
        sportsResult.setByline(stringTest);
        sportsResult.setPublishedDate(stringTest);
        sportsResult.setSection(stringTest);
        sportsResult.setTitle(stringTest);
        sportsResult.setSubsection(stringTest);
        sportsResult.setUrl(stringTest);
        sportsResult.setMultimedia(sportsMultimediaList);

        sportsResultsList.add(sportsResult);

        assertEquals(stringTest, sportsResult.getAbstract());
        assertEquals(stringTest, sportsResult.getByline());
        assertEquals(stringTest, sportsResult.getPublishedDate());
        assertEquals(stringTest, sportsResult.getSection());
        assertEquals(stringTest, sportsResult.getSubsection());
        assertEquals(stringTest, sportsResult.getAbstract());
        assertEquals(stringTest, sportsResult.getTitle());
        assertEquals(stringTest, sportsResult.getUrl());
        assertEquals(sportsMultimediaList, sportsResult.getMultimedia());
    }

    @Test
    public void SportsMain_Test() throws Exception{
        SportsMain sportsMain = new SportsMain();

        sportsMain.setNumResults(numberTest);
        sportsMain.setStatus(stringTest);
        sportsMain.setResults(sportsResultsList);

        assertEquals(stringTest, sportsMain.getStatus());
        assertEquals(numberTest, sportsMain.getNumResults());
        assertEquals(sportsResultsList, sportsMain.getResults());
    }
}
