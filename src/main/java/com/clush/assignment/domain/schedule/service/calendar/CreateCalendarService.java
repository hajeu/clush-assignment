package com.clush.assignment.domain.schedule.service.calendar;

import com.clush.assignment.domain.schedule.dto.request.CalendarReqDto;
import com.clush.assignment.domain.schedule.entity.Calendar;
import com.clush.assignment.domain.schedule.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCalendarService {

    private final CalendarRepository calendarRepository;

    public void execute(CalendarReqDto calendarReqDto) {
        Calendar calendar = new Calendar(
                calendarReqDto.title(),
                calendarReqDto.description(),
                calendarReqDto.dueDateTime()
        );

        calendarRepository.save(calendar);
    }
}
