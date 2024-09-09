package com.clush.assignment.domain.schedule.service;

import com.clush.assignment.domain.schedule.entity.Schedule;
import com.clush.assignment.domain.schedule.entity.Todo;
import com.clush.assignment.domain.schedule.repository.ScheduleRepository;
import com.clush.assignment.global.exception.ExpectedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@DisplayName("DeleteScheduleByIdService 클래스의")
public class DeleteScheduleByIdServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private DeleteScheduleByIdService deleteScheduleByIdService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("execute 메소드는")
    class Describe_execute {

        private final Long scheduleId = 1L;

        @Nested
        @DisplayName("해당 ID의 스케줄이 존재하면")
        class Context_with_existing_schedule {

            private final Schedule existingSchedule = new Todo(
                    1L,
                    "투두 제목",
                    LocalDateTime.now(),
                    false
            );

            @BeforeEach
            void setUp() {
                when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(existingSchedule));
            }

            @Test
            @DisplayName("스케줄을 삭제한다.")
            void it_delete_the_schedule() {
                deleteScheduleByIdService.execute(scheduleId);

                verify(scheduleRepository, times(1)).delete(existingSchedule);
            }
        }

        @Nested
        @DisplayName("해당 ID의 스케줄이 존재하지 않으면")
        class Context_with_non_existing_schedule {

            @BeforeEach
            void setUp() {
                when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.empty());
            }

            @Test
            @DisplayName("예외를 던진다.")
            void it_throw_exception() {
                assertThatThrownBy(() -> deleteScheduleByIdService.execute(scheduleId))
                        .isInstanceOf(ExpectedException.class)
                        .hasMessageContaining("해당 id의 schedule을 찾을 수 없습니다. id: " + scheduleId);

                verify(scheduleRepository, times(0)).delete(any(Schedule.class));
            }
        }
    }
}
