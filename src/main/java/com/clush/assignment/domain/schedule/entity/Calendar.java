package com.clush.assignment.domain.schedule.entity;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Calendar extends Schedule {

    private LocalDateTime eventDateTime;

    public Calendar(
            @NonNull String title,
            String description,
            @NonNull LocalDateTime eventDateTime
    ) {
        super(title, description);
        this.eventDateTime = eventDateTime;
    }
}
