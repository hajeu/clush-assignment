package com.clush.assignment.domain.schedule.entity;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Calendar extends Schedule {

    @Length(max = 300)
    private String description;

    public Calendar(
            @NonNull String title,
            String description,
            @NonNull LocalDateTime dueDate
    ) {
        super(title, dueDate);
        this.description = description;
    }

    public Calendar(
            @NonNull Long id,
            @NonNull String title,
            String description,
            @NonNull LocalDateTime dueDate,
            @NonNull Boolean bookMark
    ) {
        super(id, title, dueDate, bookMark);
        this.description = description;
    }
}
