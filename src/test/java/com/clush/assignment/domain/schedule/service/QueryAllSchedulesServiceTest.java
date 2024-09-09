package com.clush.assignment.domain.schedule.service;

import com.clush.assignment.domain.schedule.dto.response.ScheduleResDto;
import com.clush.assignment.domain.schedule.dto.type.ScheduleType;
import com.clush.assignment.domain.schedule.entity.Calendar;
import com.clush.assignment.domain.schedule.entity.Schedule;
import com.clush.assignment.domain.schedule.entity.Todo;
import com.clush.assignment.domain.schedule.repository.ScheduleRepository;
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
import static org.mockito.Mockito.when;

@DisplayName("QueryAllSchedulesService 클래스의")
public class QueryAllSchedulesServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private QueryAllSchedulesService queryAllSchedulesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("execute 메소드는")
    class Describe_execute {

        private final List<Schedule> schedules = new ArrayList<>();

        @BeforeEach
        void setUp() {
            LocalDateTime now = LocalDateTime.now();

            schedules.add(new Todo(1L, "투두 제목", now, false));
            schedules.add(new Calendar(2L, "캘린더 제목", "캘린더 설명", now));

            when(scheduleRepository.findAll()).thenReturn(schedules);
        }

        @Test
        @DisplayName("모든 스케줄 목록을 반환한다.")
        void it_return_list_of_all_schedules() {
            List<ScheduleResDto> result = queryAllSchedulesService.execute();

            assertThat(result).hasSize(2);

            ScheduleResDto firstSchedule = result.get(0);
            assertThat(firstSchedule.getTitle()).isEqualTo("투두 제목");
            assertThat(firstSchedule.getType()).isEqualTo(ScheduleType.TODO);

            ScheduleResDto secondSchedule = result.get(1);
            assertThat(secondSchedule.getTitle()).isEqualTo("캘린더 제목");
            assertThat(secondSchedule.getType()).isEqualTo(ScheduleType.CALENDAR);
        }
    }
}
