package com.clush.assignment.domain.schedule.service.calendar;

import com.clush.assignment.domain.schedule.dto.request.DateReqDto;
import com.clush.assignment.domain.schedule.dto.response.CalendarResDto;
import com.clush.assignment.domain.schedule.entity.Calendar;
import com.clush.assignment.domain.schedule.repository.CalendarRepository;
import com.clush.assignment.domain.schedule.util.LocalDateTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("QueryCalendarsService 클래스의")
public class QueryCalendarsServiceTest {

    @Mock
    private CalendarRepository calendarRepository;

    @InjectMocks
    private QueryCalendarsService queryCalendarsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("execute 메소드는")
    class Describe_execute {

        private final DateReqDto dateReqDto = new DateReqDto(LocalDate.of(2024, 9, 9));
        private final LocalDateTime startOfDay = LocalDateTimeUtil.startOfDay(dateReqDto.reqDate());
        private final LocalDateTime endOfDay = LocalDateTimeUtil.endOfDay(dateReqDto.reqDate());

        private final List<Calendar> calendars = new ArrayList<>();

        @BeforeEach
        void setUp() {
            calendars.add(new Calendar("회의1", "내용1", startOfDay));
            calendars.add(new Calendar("회의2", "내용", endOfDay));

            when(calendarRepository.findAllByDueDateTimeBetween(startOfDay, endOfDay)).thenReturn(calendars);
        }

        @Test
        @DisplayName("특정 날짜의 캘린더 목록을 반환한다.")
        void it_return_list_of_calendars_within_date() {
            List<CalendarResDto> result = queryCalendarsService.execute(dateReqDto);

            assertThat(result).hasSize(2);
            assertThat(result.get(0).title()).isEqualTo("회의1");
            assertThat(result.get(1).title()).isEqualTo("회의2");
        }
    }
}
