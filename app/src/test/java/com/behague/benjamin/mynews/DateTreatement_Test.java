package com.behague.benjamin.mynews;

import com.behague.benjamin.mynews.views.MostPopulars.MostPopularViewHolder;
import com.behague.benjamin.mynews.views.SearchResults.SearchResultsViewHolder;
import com.behague.benjamin.mynews.views.Sports.SportsViewHolder;
import com.behague.benjamin.mynews.views.TopStories.TopStoriesViewHolder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;


/**
 * Created by Benjamin BEHAGUE on 31/01/2018.
 */

@RunWith(JUnit4.class)
public class DateTreatement_Test {

    private String date = "2018-01-30T22:13:35-05:00";
    private String date_result;
    private final String date_Expected = "30/01/18";

    @Test
    public void dateTreatmentTopStories_Test() throws Exception{
        date_result = TopStoriesViewHolder.dateTreatement(date);
        assertEquals(date_Expected, date_result);
    }

    @Test
    public void dateTreatementMostPopular_Test() throws Exception{
        date_result = MostPopularViewHolder.dateTreatement(date);
        assertEquals(date_Expected, date_result);
    }

    @Test
    public void dateTreatmentSearch_Test() throws Exception{
        date_result = SearchResultsViewHolder.dateTreatement(date);
        assertEquals(date_Expected, date_result);
    }

    @Test
    public void dateTreatmentSport_Test() throws Exception{
        date_result = SportsViewHolder.dateTreatement(date);
        assertEquals(date_Expected, date_result);
    }
}
