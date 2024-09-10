package com.clush.assignment.domain.schedule.repository;

import com.clush.assignment.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByDueDateTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Schedule> findAllByBookMarkIsTrue();
}
