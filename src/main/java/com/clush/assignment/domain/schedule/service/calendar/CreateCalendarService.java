package com.clush.assignment.domain.schedule.service.calendar;

import com.clush.assignment.domain.schedule.dto.request.BasicCalendarReqDto;
import com.clush.assignment.domain.schedule.entity.Calendar;
import com.clush.assignment.domain.schedule.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCalendarService {

    private final CalendarRepository calendarRepository;

    public void execute(BasicCalendarReqDto basicCalendarReqDto) {
        Calendar calendar = new Calendar(
                basicCalendarReqDto.title(),
                basicCalendarReqDto.description(),
                basicCalendarReqDto.dueDateTime()
        );

        calendarRepository.save(calendar);
    }
}
