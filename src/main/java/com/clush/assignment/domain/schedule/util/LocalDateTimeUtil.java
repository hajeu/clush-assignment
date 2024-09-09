package com.clush.assignment.domain.schedule.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LocalDateTimeUtil {

    public static LocalDateTime startOfDay(LocalDate date) {
        return date.atStartOfDay();
    }

    public static LocalDateTime endOfDay(LocalDate date) {
        return date.atTime(23, 59, 59, 999999);
    }
}
