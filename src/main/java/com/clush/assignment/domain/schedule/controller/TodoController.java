package com.clush.assignment.domain.schedule.controller;

import com.clush.assignment.domain.schedule.dto.request.BasicTodoReqDto;
import com.clush.assignment.domain.schedule.dto.request.DateReqDto;
import com.clush.assignment.domain.schedule.dto.response.BasicTodoResDto;
import com.clush.assignment.domain.schedule.service.CreateTodoService;
import com.clush.assignment.domain.schedule.service.QueryAllTodoService;
import com.clush.assignment.domain.schedule.service.QueryTodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/api/v1/todo"))
@RequiredArgsConstructor
public class TodoController {

    private final CreateTodoService createTodoService;
    private final QueryTodoService queryTodoService;
    private final QueryAllTodoService queryAllTodoService;

    @PostMapping
    public ResponseEntity<Void> create(
            @RequestBody BasicTodoReqDto basicTodoReqDto
    ) {
        createTodoService.execute(basicTodoReqDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<BasicTodoResDto>> find(
            @RequestBody DateReqDto dateReqDto
    ) {
        return ResponseEntity.ok(queryTodoService.execute(dateReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BasicTodoResDto>> findAll() {
        return ResponseEntity.ok(queryAllTodoService.execute());
    }
}
