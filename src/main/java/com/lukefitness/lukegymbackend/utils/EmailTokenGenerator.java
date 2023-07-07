package com.lukefitness.lukegymbackend.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class EmailTokenGenerator {
    public static String generate(){

        return UUID.randomUUID().toString().replace("-", "");
    }

    public static Timestamp getExpirationDate(){
        LocalDateTime utcNow = LocalDateTime.now(ZoneOffset.UTC);
        return Timestamp.valueOf(utcNow.plusMinutes(15));
    }
}
