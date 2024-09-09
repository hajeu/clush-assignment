package com.clush.assignment.domain.schedule.service;

import com.clush.assignment.domain.schedule.dto.request.BasicTodoReqDto;
import com.clush.assignment.domain.schedule.dto.request.DateReqDto;
import com.clush.assignment.domain.schedule.dto.response.BasicTodoResDto;
import com.clush.assignment.domain.schedule.entity.Todo;
import com.clush.assignment.domain.schedule.repository.TodoRepository;
import com.clush.assignment.domain.schedule.util.LocalDateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryTodoService {

    private final TodoRepository todoRepository;

    public List<BasicTodoResDto> execute(DateReqDto dateReqDto) {
        LocalDate reqDate = dateReqDto.reqDate();

        List<Todo> todos = todoRepository.findAllByDueDateTimeBetween(LocalDateTimeUtil.startOfDay(reqDate), LocalDateTimeUtil.endOfDay(reqDate));

        return BasicTodoResDto.toBasicTodoResDtos(todos);
    }
}
