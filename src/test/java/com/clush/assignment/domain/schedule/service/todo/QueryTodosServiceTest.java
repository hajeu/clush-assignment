package com.clush.assignment.domain.schedule.service.todo;

import com.clush.assignment.domain.schedule.dto.request.DateReqDto;
import com.clush.assignment.domain.schedule.dto.response.TodoResDto;
import com.clush.assignment.domain.schedule.entity.Todo;
import com.clush.assignment.domain.schedule.repository.TodoRepository;
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

@DisplayName("QueryTodosService 클래스의")
public class QueryTodosServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private QueryTodosService queryTodosService;

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

        private final List<Todo> todos = new ArrayList<>();

        @BeforeEach
        void setUp() {
            todos.add(new Todo("제목1", startOfDay, false));
            todos.add(new Todo("제목2", endOfDay, true));

            when(todoRepository.findAllByDueDateTimeBetween(startOfDay, endOfDay)).thenReturn(todos);
        }

        @Test
        @DisplayName("특정 날짜의 투두 목록을 반환한다.")
        void it_return_list_of_todos_within_date() {
            List<TodoResDto> result = queryTodosService.execute(dateReqDto);

            assertThat(result).hasSize(2);
            assertThat(result.get(0).title()).isEqualTo("제목1");
            assertThat(result.get(1).title()).isEqualTo("제목2");
        }
    }
}

