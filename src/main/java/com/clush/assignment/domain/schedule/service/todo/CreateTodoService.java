package com.clush.assignment.domain.schedule.service.todo;

import com.clush.assignment.domain.schedule.dto.request.BasicTodoReqDto;
import com.clush.assignment.domain.schedule.entity.Todo;
import com.clush.assignment.domain.schedule.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTodoService {

    private final TodoRepository todoRepository;

    public void execute(BasicTodoReqDto basicTodoReqDto) {
        Todo todo = new Todo(
                basicTodoReqDto.title(),
                basicTodoReqDto.dueDate().atStartOfDay(),
                false
        );

        todoRepository.save(todo);
    }
}
