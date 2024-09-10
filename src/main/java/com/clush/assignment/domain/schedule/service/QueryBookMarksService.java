package com.clush.assignment.domain.schedule.service;

import com.clush.assignment.domain.schedule.dto.response.ScheduleResDto;
import com.clush.assignment.domain.schedule.entity.Schedule;
import com.clush.assignment.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryBookMarksService {

    private final ScheduleRepository scheduleRepository;

    public List<ScheduleResDto> execute() {
        List<Schedule> schedules = scheduleRepository.findAllByBookMarkIsTrue();

        return ScheduleResDto.toScheduleDtos(schedules);
    }
}
