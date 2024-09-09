package com.clush.assignment.domain.schedule.dto.response;

import com.clush.assignment.domain.schedule.dto.type.ScheduleType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CalendarDto extends ScheduleResDto {
    private final String description;

    public CalendarDto(Long id, String title, LocalDateTime dueDateTime, String description, ScheduleType scheduleType) {
        super(id, title, dueDateTime, scheduleType);
        this.description = description;
    }
}
