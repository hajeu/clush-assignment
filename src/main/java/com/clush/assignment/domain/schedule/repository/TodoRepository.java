package com.clush.assignment.domain.schedule.repository;

import com.clush.assignment.domain.schedule.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByDueDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
