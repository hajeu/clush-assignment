package com.clush.assignment.domain.schedule.controller;

import com.clush.assignment.domain.schedule.dto.request.BasicTodoReqDto;
import com.clush.assignment.domain.schedule.dto.request.DateReqDto;
import com.clush.assignment.domain.schedule.dto.response.BasicTodoResDto;
import com.clush.assignment.domain.schedule.service.todo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/api/v1/todo"))
@RequiredArgsConstructor
public class TodoController {

    private final CreateTodoService createTodoService;
    private final QueryTodosService queryTodosService;
    private final QueryAllTodosService queryAllTodosService;
    private final UpdateTodoByIdService updateTodoByIdService;
    private final UpdateTodoCompletedByIdService updateTodoCompletedByIdService;

    @PostMapping
    public ResponseEntity<Void> create(
            @RequestBody BasicTodoReqDto basicTodoReqDto
    ) {
        createTodoService.execute(basicTodoReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<BasicTodoResDto>> find(
            @RequestBody DateReqDto dateReqDto
    ) {
        return ResponseEntity.ok(queryTodosService.execute(dateReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BasicTodoResDto>> findAll() {
        return ResponseEntity.ok(queryAllTodosService.execute());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable("id") Long id,
            @RequestBody BasicTodoReqDto basicTodoReqDto
    ) {
        updateTodoByIdService.execute(id, basicTodoReqDto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> toggleCompleted(
            @PathVariable("id") Long id
    ) {
        updateTodoCompletedByIdService.execute(id);
        return ResponseEntity.noContent().build();
    }
}
