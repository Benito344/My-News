package com.behague.benjamin.mynews.utils;

/**
 * Created by Benjamin BEHAGUE on 03/02/2018.
 */

public class DateTreatment {

    public static String DateTreatement (String date){
        date = date.substring(0, 10);
        String[] dateSplit = date.split("-");
        String finalDate = dateSplit[2] + "/" + dateSplit[1] + "/" + dateSplit[0].substring(2, 4);

        return finalDate;
    }
}
