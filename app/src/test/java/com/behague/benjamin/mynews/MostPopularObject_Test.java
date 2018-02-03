package com.behague.benjamin.mynews;

import com.behague.benjamin.mynews.models.MostPopulars.MostPopularMain;
import com.behague.benjamin.mynews.models.MostPopulars.MostPopularMedia;
import com.behague.benjamin.mynews.models.MostPopulars.MostPopularMediaMetadata;
import com.behague.benjamin.mynews.models.MostPopulars.MostPopularResult;

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
public class MostPopularObject_Test {

    private final Integer numberTest = 1993;
    private final String stringTest = "Test Value";
    private List<MostPopularMediaMetadata> mostPopularMediaMetadataList = new ArrayList<>();
    private List<MostPopularMedia> mostPopularMediaList = new ArrayList<>();
    private List<MostPopularResult> mostPopularResultList = new ArrayList<>();

    @Test
    public void MostPopularMediaData_Test() throws Exception{
        MostPopularMediaMetadata mostPopularMediaMetadata = new MostPopularMediaMetadata();

        mostPopularMediaMetadata.setUrl(stringTest);
        mostPopularMediaMetadata.setFormat(stringTest);
        mostPopularMediaMetadata.setHeight(numberTest);
        mostPopularMediaMetadata.setWidth(numberTest);

        mostPopularMediaMetadataList.add(mostPopularMediaMetadata);

        assertEquals(stringTest, mostPopularMediaMetadata.getUrl());
        assertEquals(stringTest, mostPopularMediaMetadata.getFormat());
        assertEquals(numberTest, mostPopularMediaMetadata.getHeight());
        assertEquals(numberTest, mostPopularMediaMetadata.getWidth());
    }

    @Test
    public void MostPopularMedia_Test() throws Exception {
        MostPopularMedia mostPopularMedia = new MostPopularMedia();

        mostPopularMedia.setCaption(stringTest);
        mostPopularMedia.setMediaMetadata(mostPopularMediaMetadataList);

        mostPopularMediaList.add(mostPopularMedia);

        assertEquals(stringTest, mostPopularMedia.getCaption());
        assertEquals(mostPopularMediaMetadataList, mostPopularMedia.getMediaMetadata());
    }

    @Test
    public void MostPopularResult_Test() throws Exception{
        MostPopularResult mostPopularResult = new MostPopularResult();

        mostPopularResult.setUrl(stringTest);
        mostPopularResult.setAbstract(stringTest);
        mostPopularResult.setPublishedDate(stringTest);
        mostPopularResult.setSection(stringTest);
        mostPopularResult.setTitle(stringTest);
        mostPopularResult.setMedia(mostPopularMediaList);

        mostPopularResultList.add(mostPopularResult);

        assertEquals(stringTest, mostPopularResult.getUrl());
        assertEquals(stringTest, mostPopularResult.getAbstract());
        assertEquals(stringTest, mostPopularResult.getPublishedDate());
        assertEquals(stringTest, mostPopularResult.getSection());
        assertEquals(stringTest, mostPopularResult.getTitle());
        assertEquals(mostPopularMediaList, mostPopularResult.getMedia());
    }

    @Test
    public void MostPopularMain_Test() throws Exception{
        MostPopularMain mostPopularMain = new MostPopularMain();

        mostPopularMain.setStatus(stringTest);
        mostPopularMain.setNumResults(numberTest);
        mostPopularMain.setResults(mostPopularResultList);

        assertEquals(stringTest, mostPopularMain.getStatus());
        assertEquals(numberTest, mostPopularMain.getNumResults());
        assertEquals(mostPopularResultList, mostPopularMain.getResults());
    }
}
