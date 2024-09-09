package com.clush.assignment.domain.schedule.dto.response;

import com.clush.assignment.domain.schedule.entity.Todo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record BasicTodoResDto(
        Long id,
        String title,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime dueDateTime,
        Boolean completed
) {

    public static List<BasicTodoResDto> toBasicTodoResDtos(List<Todo> todos) {
        return todos.stream()
                .map(todo -> new BasicTodoResDto(
                        todo.getId(),
                        todo.getTitle(),
                        todo.getDueDateTime(),
                        todo.getCompleted())
                )
                .toList();
    }
}
