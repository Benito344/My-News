package com.behague.benjamin.mynews;

import com.behague.benjamin.mynews.utils.RemovedSection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by Benjamin BEHAGUE on 07/02/2018.
 */

@RunWith(JUnit4.class)
public class RemovedSection_Test {

    @Test
    public void removeSection_Test() throws Exception{
        String [] sectionsBase = new String[] {"Sports", "Travels", "Entrepreneurs", "Politics"};
        String result = "Sports,Entrepreneurs,Politics";

        assertEquals(result, RemovedSection.removeSection(sectionsBase, "Travels"));
    }
}
