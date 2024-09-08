package com.clush.assignment.domain.schedule.repository;

import com.clush.assignment.domain.schedule.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
