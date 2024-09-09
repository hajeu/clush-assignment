package com.clush.assignment.domain.schedule.service;

import com.clush.assignment.domain.schedule.dto.response.ScheduleResDto;
import com.clush.assignment.domain.schedule.entity.Schedule;
import com.clush.assignment.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryAllSchedulesService {

    private final ScheduleRepository scheduleRepository;

    public List<ScheduleResDto> execute() {
        List<Schedule> schedules = scheduleRepository.findAll();

        return ScheduleResDto.toScheduleDtos(schedules);
    }
}
