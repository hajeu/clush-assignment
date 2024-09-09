package com.clush.assignment.domain.schedule.service.calendar;

import com.clush.assignment.domain.schedule.dto.response.CalendarResDto;
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
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("QueryAllCalendarsService 클래스의")
public class QueryAllCalendarsServiceTest {

    @Mock
    private CalendarRepository calendarRepository;

    @InjectMocks
    private QueryAllCalendarsService queryAllCalendarsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("execute 메소드는")
    class Describe_execute {

        @Nested
        @DisplayName("캘린더 목록이 존재할 때")
        class Context_with_existing_calendars {

            private final List<Calendar> calendars = new ArrayList<>();

            @BeforeEach
            void setUp() {
                calendars.add(new Calendar("제목1", "내용1", LocalDateTime.now()));
                calendars.add(new Calendar("제목2", "내용2", LocalDateTime.now()));

                when(calendarRepository.findAll()).thenReturn(calendars);
            }

            @Test
            @DisplayName("캘린더 목록을 반환한다.")
            void it_return_calendar_list() {
                List<CalendarResDto> result = queryAllCalendarsService.execute();

                assertThat(result).hasSize(2);
                assertThat(result.get(0).title()).isEqualTo("제목1");
                assertThat(result.get(1).title()).isEqualTo("제목2");
            }
        }
    }
}
