package com.clush.assignment.domain.schedule.service.todo;

import com.clush.assignment.domain.schedule.dto.request.DateReqDto;
import com.clush.assignment.domain.schedule.dto.response.BasicTodoResDto;
import com.clush.assignment.domain.schedule.entity.Todo;
import com.clush.assignment.domain.schedule.repository.TodoRepository;
import com.clush.assignment.domain.schedule.util.LocalDateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryTodosService {

    private final TodoRepository todoRepository;

    @Transactional(readOnly = true)
    public List<BasicTodoResDto> execute(DateReqDto dateReqDto) {
        LocalDate reqDate = dateReqDto.reqDate();

        List<Todo> todos = todoRepository.findAllByDueDateTimeBetween(LocalDateTimeUtil.startOfDay(reqDate), LocalDateTimeUtil.endOfDay(reqDate));

        return BasicTodoResDto.toBasicTodoResDtos(todos);
    }
}
