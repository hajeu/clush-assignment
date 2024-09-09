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
    private final QueryTodoService queryTodoService;
    private final QueryAllTodoService queryAllTodoService;
    private final UpdateTodoByIdService updateTodoByIdService;
    private final UpdateTodoCompletedByIdService updateTodoCompletedByIdService;
    private final DeleteTodoByIdService deleteTodoByIdService;

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
        return ResponseEntity.ok(queryTodoService.execute(dateReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BasicTodoResDto>> findAll() {
        return ResponseEntity.ok(queryAllTodoService.execute());
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id
    ) {
        deleteTodoByIdService.execute(id);
        return ResponseEntity.noContent().build();
    }
}
