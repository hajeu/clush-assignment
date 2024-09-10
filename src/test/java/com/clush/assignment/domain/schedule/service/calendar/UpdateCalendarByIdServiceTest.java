package com.clush.assignment.domain.schedule.service.calendar;

import com.clush.assignment.domain.schedule.dto.request.CalendarReqDto;
import com.clush.assignment.domain.schedule.entity.Calendar;
import com.clush.assignment.domain.schedule.repository.CalendarRepository;
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
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("UpdateCalendarByIdService 클래스의")
public class UpdateCalendarByIdServiceTest {

    @Mock
    private CalendarRepository calendarRepository;

    @InjectMocks
    private UpdateCalendarByIdService updateCalendarByIdService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("execute 메소드는")
    class Describe_execute {

        private final Long calendarId = 1L;
        private final CalendarReqDto calendarReqDto = new CalendarReqDto(
                "변경할 제목",
                "변경할 설명",
                LocalDateTime.of(2024, 9, 9, 10, 0)
        );

        @Nested
        @DisplayName("해당 ID의 캘린더가 존재하면")
        class Context_with_existing_calendar {

            private final Calendar existingCalendar = new Calendar(
                    calendarId,
                    "기존의 제목",
                    "기존의 설명",
                    LocalDateTime.of(2024, 9, 10, 8 ,15),
                    false
            );

            @BeforeEach
            void setUp() {
                when(calendarRepository.findById(calendarId)).thenReturn(Optional.of(existingCalendar));
            }

            @Test
            @DisplayName("캘린더를 업데이트하고 저장한다.")
            void it_update_and_save_the_calendar() {
                updateCalendarByIdService.execute(calendarId, calendarReqDto);

                verify(calendarRepository, times(1)).save(any(Calendar.class));
            }
        }

        @Nested
        @DisplayName("해당 ID의 캘린더가 존재하지 않으면")
        class Context_with_non_existing_calendar {

            @BeforeEach
            void setUp() {
                when(calendarRepository.existsById(calendarId)).thenReturn(false);
            }

            @Test
            @DisplayName("예외를 던진다.")
            void it_throw_exception() {
                assertThatThrownBy(() -> updateCalendarByIdService.execute(calendarId, calendarReqDto))
                        .isInstanceOf(ExpectedException.class)
                        .hasMessageContaining("해당 id의 calendar를 찾을 수 없습니다. id: " + calendarId);

                verify(calendarRepository, times(0)).save(any(Calendar.class));
            }
        }
    }
}
