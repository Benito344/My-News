package com.behague.benjamin.mynews;

import com.behague.benjamin.mynews.utils.DateTreatment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;


/**
 * Created by Benjamin BEHAGUE on 31/01/2018.
 */

@RunWith(JUnit4.class)
public class DateTreatement_Test {

    @Test
    public void DateTreatment_Test() throws Exception{
        String date = "2018-01-30T22:13:35-05:00";
        String date_result;
        final String date_Expected = "30/01/18";

        date_result = DateTreatment.DateTreatement(date);
        assertEquals(date_Expected, date_result);
    }

}
