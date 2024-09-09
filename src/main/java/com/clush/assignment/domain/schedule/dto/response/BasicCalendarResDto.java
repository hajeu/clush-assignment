package com.clush.assignment.domain.schedule.dto.response;

import com.clush.assignment.domain.schedule.entity.Calendar;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record BasicCalendarResDto(
        Long id,
        String title,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime dueDateTime,
        String description
) {

    public static List<BasicCalendarResDto> toBasicCalendarResDtos(List<Calendar> calendars) {
        return calendars.stream()
                .map(calendar -> new BasicCalendarResDto(
                        calendar.getId(),
                        calendar.getTitle(),
                        calendar.getDueDateTime(),
                        calendar.getDescription())
                )
                .toList();
    }
}
