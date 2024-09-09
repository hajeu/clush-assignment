package com.clush.assignment.domain.schedule.service.calendar;

import com.clush.assignment.domain.schedule.dto.request.CalendarReqDto;
import com.clush.assignment.domain.schedule.entity.Calendar;
import com.clush.assignment.domain.schedule.repository.CalendarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("CreateCalendarService 클래스의")
public class CreateCalendarServiceTest {

    @Mock
    private CalendarRepository calendarRepository;

    @InjectMocks
    private CreateCalendarService createCalendarService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("execute 메소드는")
    class Describe_execute {

        private final CalendarReqDto calendarReqDto = new CalendarReqDto(
                "제목",
                "내용",
                LocalDateTime.of(2024, 9, 9, 12, 12)
        );

        @Nested
        @DisplayName("유효한 요청 데이터가 주어지면")
        class Context_with_valid_request_data {

            @Test
            @DisplayName("새로운 캘린더를 생성하고 저장한다.")
            void it_create_and_save_new_calendar() {
                createCalendarService.execute(calendarReqDto);

                verify(calendarRepository, times(1)).save(any(Calendar.class));
            }
        }
    }
}

