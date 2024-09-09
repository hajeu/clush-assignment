package com.clush.assignment.domain.schedule.service.todo;

import com.clush.assignment.domain.schedule.dto.request.TodoReqDto;
import com.clush.assignment.domain.schedule.entity.Todo;
import com.clush.assignment.domain.schedule.repository.TodoRepository;
import com.clush.assignment.global.exception.ExpectedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("UpdateTodoByIdService 클래스의")
public class UpdateTodoByIdServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private UpdateTodoByIdService updateTodoByIdService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("execute 메소드는")
    class Describe_execute {

        private final Long todoId = 1L;
        private final TodoReqDto todoReqDto = new TodoReqDto(
                "변경할 제목",
                LocalDate.of(2024, 9, 9)
        );

        @Nested
        @DisplayName("해당 ID의 투두가 존재하면")
        class Context_with_existing_todo {

            private final Todo existingTodo = new Todo(
                    todoId,
                    "기존의 제목",
                    LocalDate.of(2024, 9, 9).atStartOfDay(),
                    false
            );

            @BeforeEach
            void setUp() {
                when(todoRepository.findById(todoId)).thenReturn(Optional.of(existingTodo));
            }

            @Test
            @DisplayName("투두를 업데이트하고 저장한다.")
            void it_update_and_save_the_todo() {
                updateTodoByIdService.execute(todoId, todoReqDto);

                verify(todoRepository, times(1)).save(any(Todo.class));  // 저장이 호출되었는지 검증
            }
        }

        @Nested
        @DisplayName("해당 ID의 투두가 존재하지 않으면")
        class Context_with_non_existing_todo {

            @BeforeEach
            void setUp() {
                when(todoRepository.findById(todoId)).thenReturn(Optional.empty());
            }

            @Test
            @DisplayName("예외를 던진다.")
            void it_throw_exception() {
                assertThatThrownBy(() -> updateTodoByIdService.execute(todoId, todoReqDto))
                        .isInstanceOf(ExpectedException.class)
                        .hasMessageContaining("해당 id의 todo를 찾을 수 없습니다. id: " + todoId);

                verify(todoRepository, times(0)).save(any(Todo.class));  // 저장이 호출되지 않았는지 검증
            }
        }
    }
}

