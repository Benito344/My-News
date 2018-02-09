package com.behague.benjamin.mynews.utils;

/**
 * Created by Benjamin BEHAGUE on 03/02/2018.
 */

public class DateTreatment {

    //This function is used for transform date : 2018-01-30T22:13:35-05:00 into : 30/01/18
    public static String DateTreatement (String date){
        date = date.substring(2, 10);
        String[] dateSplit = date.split("-");

        return dateSplit[2] + "/" + dateSplit[1] + "/" + dateSplit[0];
    }
}
