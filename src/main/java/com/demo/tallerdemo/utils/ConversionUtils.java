package com.demo.tallerdemo.utils;

import java.time.*;

public class ConversionUtils {

    public static Instant convertStringStartToInstant (String date) {
        if (date == null) {
            return Instant.EPOCH;
//            return Instant.ofEpochMilli(Long.MIN_VALUE);
        }
        LocalDate localDate = LocalDate.parse(date);
        return localDate.atStartOfDay().toInstant(ZoneOffset.UTC);
    }

    public static Instant convertStringEndToInstant (String date) {
        if (date == null) {
            return LocalDate.now().atTime(LocalTime.MAX).toInstant(ZoneOffset.UTC);
        }
        LocalDate localDate = LocalDate.parse(date);
        return localDate.atTime(LocalTime.MAX).toInstant(ZoneOffset.UTC);
    }

}
