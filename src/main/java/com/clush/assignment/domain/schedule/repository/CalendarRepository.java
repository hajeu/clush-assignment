package com.clush.assignment.domain.schedule.repository;

import com.clush.assignment.domain.schedule.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}
