package com.clush.assignment.domain.schedule.controller;

import com.clush.assignment.domain.schedule.dto.BasicTodoReqDto;
import com.clush.assignment.domain.schedule.service.CreateTodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/api/v1/todo"))
@RequiredArgsConstructor
public class TodoController {

    private final CreateTodoService createTodoService;

    @PostMapping("/create")
    public ResponseEntity<Void> create(
            @RequestBody BasicTodoReqDto basicTodoReqDto
    ) {
        createTodoService.execute(basicTodoReqDto);
        return ResponseEntity.ok().build();
    }
}
