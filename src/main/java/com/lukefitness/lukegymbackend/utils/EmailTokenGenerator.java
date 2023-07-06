package com.lukefitness.lukegymbackend.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Locale;

public class EmailTokenGenerator {
    public static String generate(){

        return RandomStringUtils.randomAlphanumeric(6).toUpperCase(Locale.ROOT);
    }

    public static Timestamp getExpirationDate(){
        LocalDateTime utcNow = LocalDateTime.now(ZoneOffset.UTC);
        return Timestamp.valueOf(utcNow.plusMinutes(15));
    }
}
