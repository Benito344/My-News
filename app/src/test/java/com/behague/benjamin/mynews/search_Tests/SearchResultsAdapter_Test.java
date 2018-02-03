package com.behague.benjamin.mynews.search_Tests;

import com.behague.benjamin.mynews.models.SearchArticles.SearchArticlesDoc;
import com.behague.benjamin.mynews.views.SearchResults.SearchResultsAdapter;

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
public class SearchResultsAdapter_Test {

    private final String stringTest = "Test Value";
    private List<SearchArticlesDoc> searchArticlesDocList = new ArrayList<>();

    @Before
    public void SetUp() throws Exception{
        SearchArticlesDoc searchArticlesDoc = new SearchArticlesDoc();

        searchArticlesDoc.setAbstract(stringTest);
        searchArticlesDoc.setPubDate(stringTest);
        searchArticlesDoc.setSectionName(stringTest);
        searchArticlesDoc.setSubsectionName(stringTest);
        searchArticlesDoc.setWebUrl(stringTest);

        searchArticlesDocList.add(searchArticlesDoc);

        SearchResultsAdapter.searchArticlesDocs = new ArrayList<>();
        SearchResultsAdapter.searchArticlesDocs.addAll(searchArticlesDocList);
    }

    @Test
    public void SearchArticleAdapterGetURL_Test() throws Exception{
        assertEquals(stringTest, SearchResultsAdapter.getURL(0));
    }
}
