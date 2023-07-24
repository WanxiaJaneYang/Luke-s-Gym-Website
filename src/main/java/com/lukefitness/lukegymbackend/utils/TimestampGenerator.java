package com.lukefitness.lukegymbackend.utils;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class TimestampGenerator {
    public static java.sql.Timestamp getCurrentTimestamp() {
        OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC);
        return Timestamp.from(utc.toInstant());
    }
}
