package com.clush.assignment.domain.schedule.dto.response;

import com.clush.assignment.domain.schedule.dto.type.ScheduleType;
import com.clush.assignment.domain.schedule.entity.Calendar;
import com.clush.assignment.domain.schedule.entity.Schedule;
import com.clush.assignment.domain.schedule.entity.Todo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ScheduleResDto {
    private Long id;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDateTime;
    private ScheduleType type;
    private Boolean bookMark;

    public static List<ScheduleResDto> toScheduleDtos(List<Schedule> schedules) {
        return schedules.stream()
                .map(schedule -> {
                    if (schedule instanceof Todo todo) {
                        return new TodoDto(
                                todo.getId(),
                                todo.getTitle(),
                                todo.getDueDateTime(),
                                todo.getCompleted(),
                                ScheduleType.TODO,
                                todo.getBookMark()
                        );
                    } else if (schedule instanceof Calendar calendar) {
                        return new CalendarDto(
                                calendar.getId(),
                                calendar.getTitle(),
                                calendar.getDueDateTime(),
                                calendar.getDescription(),
                                ScheduleType.CALENDAR,
                                calendar.getBookMark()
                        );
                    }
                    throw new IllegalArgumentException("잘못된 스케줄 타입입니다.");
                }).toList();
    }
}
