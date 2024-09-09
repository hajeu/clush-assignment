package com.clush.assignment.domain.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record BasicTodoReqDto(
        @NotBlank
        @Size(max = 20)
        String title,
        @NotNull
        LocalDateTime dueDateTime
) {
}
