package com.clush.assignment.domain.schedule.service;

import com.clush.assignment.domain.schedule.dto.response.BasicTodoResDto;
import com.clush.assignment.domain.schedule.entity.Todo;
import com.clush.assignment.domain.schedule.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryAllTodoService {

    private final TodoRepository todoRepository;

    public List<BasicTodoResDto> execute() {
        List<Todo> todos = todoRepository.findAll();

        return BasicTodoResDto.toBasicTodoResDtos(todos);
    }
}
