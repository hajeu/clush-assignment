package com.clush.assignment.domain.schedule.dto.response;

import com.clush.assignment.domain.schedule.entity.Todo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record TodoResDto(
        Long id,
        String title,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime dueDateTime,
        Boolean completed
) {

    public static List<TodoResDto> toBasicTodoResDtos(List<Todo> todos) {
        return todos.stream()
                .map(todo -> new TodoResDto(
                        todo.getId(),
                        todo.getTitle(),
                        todo.getDueDateTime(),
                        todo.getCompleted())
                )
                .toList();
    }
}
