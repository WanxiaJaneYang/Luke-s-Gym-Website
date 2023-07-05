package com.lukefitness.lukegymbackend.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionUtils {
    public static String extractKeywordFromSQLException(String message) {
        Pattern pattern = Pattern.compile("Column '(.*)' cannot be null");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }
}
