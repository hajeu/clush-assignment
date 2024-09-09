package com.clush.assignment.domain.schedule.service.calendar;

import com.clush.assignment.domain.schedule.dto.response.BasicCalendarResDto;
import com.clush.assignment.domain.schedule.entity.Calendar;
import com.clush.assignment.domain.schedule.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryAllCalendarsService {

    private final CalendarRepository calendarRepository;

    public List<BasicCalendarResDto> execute() {
        List<Calendar> calendars = calendarRepository.findAll();

        return BasicCalendarResDto.toBasicCalendarResDtos(calendars);
    }
}
