package com.clush.assignment.domain.schedule.service.calendar;

import com.clush.assignment.domain.schedule.dto.request.DateReqDto;
import com.clush.assignment.domain.schedule.dto.response.CalendarResDto;
import com.clush.assignment.domain.schedule.entity.Calendar;
import com.clush.assignment.domain.schedule.repository.CalendarRepository;
import com.clush.assignment.domain.schedule.util.LocalDateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryCalendarsService {

    private final CalendarRepository calendarRepository;

    @Transactional(readOnly = true)
    public List<CalendarResDto> execute(DateReqDto dateReqDto) {
        LocalDate reqDate = dateReqDto.reqDate();

        List<Calendar> calendars = calendarRepository.findAllByDueDateTimeBetween(LocalDateTimeUtil.startOfDay(reqDate), LocalDateTimeUtil.endOfDay(reqDate));

        return CalendarResDto.toBasicCalendarResDtos(calendars);
    }
}
