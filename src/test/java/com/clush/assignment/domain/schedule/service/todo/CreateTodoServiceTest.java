package com.clush.assignment.domain.schedule.service.todo;

import com.clush.assignment.domain.schedule.dto.request.TodoReqDto;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("CreateTodoService 클래스의")
public class CreateTodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private CreateTodoService createTodoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("execute 메소드는")
    class Describe_execute {

        private final TodoReqDto todoReqDto = new TodoReqDto(
                "제목",
                LocalDate.of(2024,9,9)
        );

        @Nested
        @DisplayName("유효한 요청 데이터가 주어지면")
        class Context_with_valid_request_data {

            @Test
            @DisplayName("새로운 투두를 생성하고 저장한다.")
            void it_create_and_save_new_todo() {
                createTodoService.execute(todoReqDto);

                verify(todoRepository, times(1)).save(any(Todo.class));
            }
        }
    }
}
