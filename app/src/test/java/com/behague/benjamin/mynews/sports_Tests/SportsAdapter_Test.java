package com.behague.benjamin.mynews.sports_Tests;

import com.behague.benjamin.mynews.models.Sports.SportsResult;
import com.behague.benjamin.mynews.views.Sports.SportsAdapter;

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
public class SportsAdapter_Test {

    private final String stringTest = "Test Value";
    private List<SportsResult> sportsResultsList = new ArrayList<>();

    @Before
    public void SetUp() throws Exception {
        SportsResult sportsResult = new SportsResult();

        sportsResult.setAbstract(stringTest);
        sportsResult.setByline(stringTest);
        sportsResult.setPublishedDate(stringTest);
        sportsResult.setSection(stringTest);
        sportsResult.setTitle(stringTest);
        sportsResult.setSubsection(stringTest);
        sportsResult.setUrl(stringTest);

        sportsResultsList.add(sportsResult);

        SportsAdapter.sportsList = new ArrayList<>();
        SportsAdapter.sportsList.addAll(sportsResultsList);
    }

    @Test
    public void MostPopularAdapterGetURL_Test() throws Exception{
        assertEquals(stringTest, SportsAdapter.getURL(0));
    }
}
