package com.lukefitness.lukegymbackend.utils;

public class DeactivationDateCalculator {
    public static java.sql.Timestamp calculate() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add(java.util.Calendar.DATE, 3);
        return new java.sql.Timestamp(calendar.getTime().getTime());
    }
}
