package com.clush.assignment.domain.schedule.service;

import com.clush.assignment.domain.schedule.dto.response.ScheduleResDto;
import com.clush.assignment.domain.schedule.entity.Calendar;
import com.clush.assignment.domain.schedule.entity.Schedule;
import com.clush.assignment.domain.schedule.entity.Todo;
import com.clush.assignment.domain.schedule.repository.CalendarRepository;
import com.clush.assignment.domain.schedule.repository.ScheduleRepository;
import com.clush.assignment.domain.schedule.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuerySuggestionService {

    private final TodoRepository todoRepository;
    private final CalendarRepository calendarRepository;
    private final ScheduleRepository scheduleRepository;

    public List<ScheduleResDto> execute() {
        LocalDateTime now = LocalDateTime.now();
        List<Todo> todoSchedules = todoRepository.findAllByDueDateTimeBetween(now.minusDays(3), now);
        List<Calendar> calendarSchedules = calendarRepository.findAllByDueDateTimeBetween(now.minusMonths(1), now);
        List<Schedule> bookMarks = scheduleRepository.findAllByBookMarkIsTrue();

        List<Schedule> randomSchedules = new ArrayList<>();
        randomSchedules.addAll(getTwoRandomElements(todoSchedules));
        randomSchedules.addAll(getTwoRandomElements(calendarSchedules));
        randomSchedules.addAll(getTwoRandomElements(bookMarks));

        return ScheduleResDto.toScheduleDtos(randomSchedules);
    }

    private <T> List<T> getTwoRandomElements(List<T> elements) {
        if (elements.size() <= 2) {
            return new ArrayList<>(elements);
        }
        Collections.shuffle(elements);
        return elements.subList(0, 2);
    }
}
