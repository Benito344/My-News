package com.behague.benjamin.mynews.utils;

/**
 * Created by Benjamin BEHAGUE on 07/02/2018.
 */

public class RemovedSection {
    //Used when user unchecked section
    public static String removeSection(String[] input, String deleteMe) {

        StringBuilder result = new StringBuilder();

        for(String item : input){
            if(!deleteMe.equals(item)){
                result.append(item + ",");
            }
        }

        result.deleteCharAt(result.length()-1);

        return result.toString();
    }
}
