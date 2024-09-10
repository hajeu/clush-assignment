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
public final class Todo extends Schedule {

    private Boolean completed;

    public Todo(
            @NonNull String title,
            @NonNull LocalDateTime dueDateTime,
            @NonNull Boolean completed
    ) {
        super(title, dueDateTime);
        this.completed = completed;
    }

    public Todo(
            @NonNull Long id,
            @NonNull String title,
            @NonNull LocalDateTime dueDateTime,
            @NonNull Boolean completed,
            @NonNull Boolean bookMark
    ) {
        super(id, title, dueDateTime, bookMark);
        this.completed = completed;
    }

    public void toggleCompleted() {
        this.completed = !this.completed;
    }
}
