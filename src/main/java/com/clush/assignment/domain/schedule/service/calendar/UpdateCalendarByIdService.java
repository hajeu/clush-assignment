package com.clush.assignment.domain.schedule.service.calendar;

import com.clush.assignment.domain.schedule.dto.request.CalendarReqDto;
import com.clush.assignment.domain.schedule.entity.Calendar;
import com.clush.assignment.domain.schedule.repository.CalendarRepository;
import com.clush.assignment.global.exception.ExpectedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCalendarByIdService {

    private final CalendarRepository calendarRepository;

    public void execute(Long id, CalendarReqDto calendarReqDto) {
        if (!calendarRepository.existsById(id)) {
            throw new ExpectedException("해당 id의 calendar가 존재하지 않습니다. id: " + id, HttpStatus.NOT_FOUND);
        }

        Calendar updatedCalendar = new Calendar(
                id,
                calendarReqDto.title(),
                calendarReqDto.description(),
                calendarReqDto.dueDateTime()
        );

        calendarRepository.save(updatedCalendar);
    }
}
