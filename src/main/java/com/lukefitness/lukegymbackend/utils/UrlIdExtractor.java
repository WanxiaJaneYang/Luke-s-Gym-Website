package com.lukefitness.lukegymbackend.utils;

public class UrlIdExtractor {
    public static String extract(String pathInfo, String prefix){
        int index=pathInfo.indexOf(prefix);
        String restStr=pathInfo.substring(index+prefix.length());
        int end=restStr.indexOf("/");
        return restStr.substring(0, end);
    }
}
