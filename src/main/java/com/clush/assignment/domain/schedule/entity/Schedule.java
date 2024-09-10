package com.clush.assignment.domain.schedule.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max = 20)
    private String title;

    @NotNull
    private LocalDateTime dueDateTime;

    @NotNull
    private Boolean bookMark;

    public Schedule(
            @NonNull String title,
            @NonNull LocalDateTime dueDateTime
    ) {
        this.title = title;
        this.dueDateTime = dueDateTime;
        this.bookMark = false;
    }

    public Schedule(
            @NonNull Long id,
            @NonNull String title,
            @NonNull LocalDateTime dueDateTime,
            @NonNull Boolean bookMark
    ) {
        this.id = id;
        this.title = title;
        this.dueDateTime = dueDateTime;
        this.bookMark = bookMark;
    }

    public void toggleBookMark() {
        this.bookMark = !this.bookMark;
    }
}

