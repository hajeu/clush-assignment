package com.clush.assignment.domain.schedule.service.todo;

import com.clush.assignment.domain.schedule.dto.request.TodoReqDto;
import com.clush.assignment.domain.schedule.entity.Todo;
import com.clush.assignment.domain.schedule.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTodoService {

    private final TodoRepository todoRepository;

    public void execute(TodoReqDto todoReqDto) {
        Todo todo = new Todo(
                todoReqDto.title(),
                todoReqDto.dueDate().atStartOfDay(),
                false
        );

        todoRepository.save(todo);
    }
}
