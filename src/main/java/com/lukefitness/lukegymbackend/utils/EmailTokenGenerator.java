package com.lukefitness.lukegymbackend.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class EmailTokenGenerator {
    public static String generate(){

        return RandomStringUtils.randomAlphanumeric(6);
    }

    public static Timestamp getExpirationDate(){
        return Timestamp.valueOf(LocalDateTime.now().plusMinutes(15));
    }
}
