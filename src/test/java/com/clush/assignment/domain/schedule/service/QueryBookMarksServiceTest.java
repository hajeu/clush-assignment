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

@DisplayName("QueryBookMarksService 클래스의")
public class QueryBookMarksServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private QueryBookMarksService queryBookMarksService;

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

            schedules.add(new Todo(1L, "북마크된 투두", now, false, true));
            schedules.add(new Calendar(2L, "북마크 안된 캘린더", "캘린더 설명", now, false));

            when(scheduleRepository.findAllByBookMarkIsTrue()).thenReturn(List.of(schedules.get(0)));
        }

        @Test
        @DisplayName("북마크가 된 스케줄만 반환한다.")
        void it_return_only_bookmarked_schedules() {
            List<ScheduleResDto> result = queryBookMarksService.execute();

            assertThat(result).hasSize(1);

            ScheduleResDto firstSchedule = result.get(0);
            assertThat(firstSchedule.getTitle()).isEqualTo("북마크된 투두");
            assertThat(firstSchedule.getType()).isEqualTo(ScheduleType.TODO);
        }
    }
}

