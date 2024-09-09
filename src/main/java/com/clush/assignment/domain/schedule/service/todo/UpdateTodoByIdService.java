package com.clush.assignment.domain.schedule.service.todo;

import com.clush.assignment.domain.schedule.dto.request.TodoReqDto;
import com.clush.assignment.domain.schedule.entity.Todo;
import com.clush.assignment.domain.schedule.repository.TodoRepository;
import com.clush.assignment.global.exception.ExpectedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateTodoByIdService {

    private final TodoRepository todoRepository;

    public void execute(Long id, TodoReqDto todoReqDto) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new ExpectedException("해당 id의 todo를 찾을 수 없습니다. id: " + id, HttpStatus.NOT_FOUND));

        Todo updatedTodo = new Todo(
                id,
                todoReqDto.title(),
                todoReqDto.dueDate().atStartOfDay(),
                todo.getCompleted()
        );

        todoRepository.save(updatedTodo);
    }
}
