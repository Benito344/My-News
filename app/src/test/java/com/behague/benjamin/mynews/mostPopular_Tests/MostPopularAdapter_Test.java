package com.behague.benjamin.mynews.mostPopular_Tests;

import com.behague.benjamin.mynews.models.MostPopulars.MostPopularResult;
import com.behague.benjamin.mynews.views.MostPopulars.MostPopularAdapter;

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
public class MostPopularAdapter_Test {

    private final String stringTest = "Test Value";
    private List<MostPopularResult> mostPopularResultsList = new ArrayList<>();

    @Before
    public void SetUp() throws Exception {
        MostPopularResult mostPopularResult = new MostPopularResult();

        mostPopularResult.setUrl(stringTest);
        mostPopularResult.setAbstract(stringTest);
        mostPopularResult.setPublishedDate(stringTest);
        mostPopularResult.setSection(stringTest);
        mostPopularResult.setTitle(stringTest);

        mostPopularResultsList.add(mostPopularResult);

        MostPopularAdapter.mostPopularList = new ArrayList<>();
        MostPopularAdapter.mostPopularList.addAll(mostPopularResultsList);
    }

    @Test
    public void MostPopularAdapterGetURL_Test() throws Exception{
        assertEquals(stringTest, MostPopularAdapter.getUrl(0));
    }
}
