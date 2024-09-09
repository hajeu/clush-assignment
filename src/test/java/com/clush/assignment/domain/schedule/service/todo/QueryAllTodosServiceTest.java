package com.clush.assignment.domain.schedule.service.todo;

import com.clush.assignment.domain.schedule.dto.response.TodoResDto;
import com.clush.assignment.domain.schedule.entity.Todo;
import com.clush.assignment.domain.schedule.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("QueryAllTodosService 클래스의")
public class QueryAllTodosServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private QueryAllTodosService queryAllTodosService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("execute 메소드는")
    class Describe_execute {

        @Nested
        @DisplayName("투두 목록이 존재하면")
        class Context_with_existing_todos {

            private final List<Todo> todos = new ArrayList<>();

            @BeforeEach
            void setUp() {
                todos.add(new Todo("제목1", LocalDate.now().atStartOfDay(), false));
                todos.add(new Todo("제목2", LocalDate.now().atStartOfDay(), true));

                when(todoRepository.findAll()).thenReturn(todos);
            }

            @Test
            @DisplayName("투두 목록을 반환한다.")
            void it_return_list_of_todos() {
                List<TodoResDto> result = queryAllTodosService.execute();

                assertThat(result).hasSize(2);
                assertThat(result.get(0).title()).isEqualTo("제목1");
                assertThat(result.get(1).title()).isEqualTo("제목2");
            }
        }
    }
}

