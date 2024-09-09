package com.clush.assignment.domain.schedule.service;

import com.clush.assignment.domain.schedule.entity.Schedule;
import com.clush.assignment.domain.schedule.repository.ScheduleRepository;
import com.clush.assignment.global.exception.ExpectedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteScheduleByIdService {

    private final ScheduleRepository scheduleRepository;

    public void execute(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new ExpectedException("해당 id의 schedule을 찾을 수 없습니다. id: " + id, HttpStatus.NOT_FOUND));

        scheduleRepository.delete(schedule);
    }
}
