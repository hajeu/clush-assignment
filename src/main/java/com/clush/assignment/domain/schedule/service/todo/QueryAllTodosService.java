package com.clush.assignment.domain.schedule.service.todo;

import com.clush.assignment.domain.schedule.dto.response.TodoResDto;
import com.clush.assignment.domain.schedule.entity.Todo;
import com.clush.assignment.domain.schedule.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryAllTodosService {

    private final TodoRepository todoRepository;

    @Transactional(readOnly = true)
    public List<TodoResDto> execute() {
        List<Todo> todos = todoRepository.findAll();

        return TodoResDto.toBasicTodoResDtos(todos);
    }
}
