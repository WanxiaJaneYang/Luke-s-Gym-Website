package com.lukefitness.lukegymbackend.utils;

public class UrlIdExtractor {
    public static String extract(String pathInfo, String prefix){
        int index=pathInfo.indexOf(prefix);
        String restStr=pathInfo.substring(index+prefix.length());
        int end=restStr.indexOf("/");
        if(end==-1)
            return restStr;
        else if(end==0)
            return null;
        else
            return restStr.substring(0, end);
    }
}
