package com.clush.assignment.domain.schedule.service;

import com.clush.assignment.domain.schedule.dto.response.ScheduleResDto;
import com.clush.assignment.domain.schedule.dto.request.DateReqDto;
import com.clush.assignment.domain.schedule.entity.Schedule;
import com.clush.assignment.domain.schedule.repository.ScheduleRepository;
import com.clush.assignment.domain.schedule.util.LocalDateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuerySchedulesService {

    private final ScheduleRepository scheduleRepository;

    public List<ScheduleResDto> execute(DateReqDto dateReqDto) {
        LocalDate reqDate = dateReqDto.reqDate();

        List<Schedule> schedules = scheduleRepository.findAllByDueDateTimeBetween(LocalDateTimeUtil.startOfDay(reqDate), LocalDateTimeUtil.endOfDay(reqDate));

        return ScheduleResDto.toScheduleDtos(schedules);
    }
}
