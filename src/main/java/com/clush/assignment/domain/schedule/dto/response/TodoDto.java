package com.clush.assignment.domain.schedule.dto.response;

import com.clush.assignment.domain.schedule.dto.type.ScheduleType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoDto extends ScheduleResDto {
    private final Boolean completed;

    public TodoDto(Long id, String title, LocalDateTime dueDateTime, Boolean completed, ScheduleType scheduleType, Boolean bookMark) {
        super(id, title, dueDateTime, scheduleType, bookMark);
        this.completed = completed;
    }
}
