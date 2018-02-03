package com.behague.benjamin.mynews;

import com.behague.benjamin.mynews.models.SearchArticles.SearchArticlesDoc;
import com.behague.benjamin.mynews.models.SearchArticles.SearchArticlesHeadline;
import com.behague.benjamin.mynews.models.SearchArticles.SearchArticlesMain;
import com.behague.benjamin.mynews.models.SearchArticles.SearchArticlesMultimedia;
import com.behague.benjamin.mynews.models.SearchArticles.SearchArticlesResponse;

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
public class SearchArticlesObject_Test {

    private final Integer numberTest = 1993;
    private final String stringTest = "Test Value";
    private List<SearchArticlesMultimedia> searchArticlesMultimediaList = new ArrayList<>();
    private List<SearchArticlesDoc> searchArticlesDocList = new ArrayList<>();
    private SearchArticlesResponse searchArticlesResponse = new SearchArticlesResponse();
    private SearchArticlesHeadline searchArticlesHeadline = new SearchArticlesHeadline();

    @Test
    public void SearchArticlesMultimedia_Test() throws Exception{
        SearchArticlesMultimedia searchArticlesMultimedia = new SearchArticlesMultimedia();

        searchArticlesMultimedia.setUrl(stringTest);
        searchArticlesMultimedia.setCaption(stringTest);
        searchArticlesMultimedia.setSubtype(stringTest);
        searchArticlesMultimedia.setHeight(numberTest);
        searchArticlesMultimedia.setWidth(numberTest);

        searchArticlesMultimediaList.add(searchArticlesMultimedia);

        assertEquals(stringTest, searchArticlesMultimedia.getUrl());
        assertEquals(stringTest, searchArticlesMultimedia.getCaption());
        assertEquals(stringTest, searchArticlesMultimedia.getSubtype());
        assertEquals(numberTest, searchArticlesMultimedia.getHeight());
        assertEquals(numberTest, searchArticlesMultimedia.getWidth());
    }

    @Test
    public void SearchArticlesHeadline_Test() throws Exception{
        searchArticlesHeadline.setMain(stringTest);
        searchArticlesHeadline.setKicker(stringTest);

        assertEquals(stringTest, searchArticlesHeadline.getMain());
        assertEquals(stringTest, searchArticlesHeadline.getKicker());
    }

    @Test
    public void SearchArticlesDoc_Test() throws Exception{
        SearchArticlesDoc searchArticlesDoc = new SearchArticlesDoc();

        searchArticlesDoc.setAbstract(stringTest);
        searchArticlesDoc.setPubDate(stringTest);
        searchArticlesDoc.setSectionName(stringTest);
        searchArticlesDoc.setSubsectionName(stringTest);
        searchArticlesDoc.setWebUrl(stringTest);
        searchArticlesDoc.setHeadline(searchArticlesHeadline);
        searchArticlesDoc.setMultimedia(searchArticlesMultimediaList);

        searchArticlesDocList.add(searchArticlesDoc);

        assertEquals(stringTest, searchArticlesDoc.getAbstract());
        assertEquals(stringTest, searchArticlesDoc.getPubDate());
        assertEquals(stringTest, searchArticlesDoc.getSectionName());
        assertEquals(stringTest, searchArticlesDoc.getSubsectionName());
        assertEquals(searchArticlesHeadline, searchArticlesDoc.getHeadline());
        assertEquals(searchArticlesMultimediaList, searchArticlesDoc.getMultimedia());
    }

    @Test
    public void SearchArticlesResponse_Test() throws Exception{
        searchArticlesResponse.setDocs(searchArticlesDocList);

        assertEquals(searchArticlesDocList, searchArticlesResponse.getDocs());
    }

    @Test
    public void SearchArticlesMain_Test() throws Exception{
        SearchArticlesMain searchArticlesMain = new SearchArticlesMain();

        searchArticlesMain.setResponse(searchArticlesResponse);

        assertEquals(searchArticlesResponse, searchArticlesMain.getResponse());
    }
}
