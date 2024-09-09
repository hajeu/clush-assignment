package com.clush.assignment.domain.schedule.repository;

import com.clush.assignment.domain.schedule.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    List<Calendar> findAllByDueDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
