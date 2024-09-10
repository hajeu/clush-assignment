package com.clush.assignment.domain.schedule.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DateReqDto(
        @NotNull
        LocalDate reqDate
) {
}
