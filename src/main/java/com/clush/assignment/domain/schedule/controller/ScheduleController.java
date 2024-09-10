package com.clush.assignment.domain.schedule.controller;

import com.clush.assignment.domain.schedule.dto.response.ScheduleResDto;
import com.clush.assignment.domain.schedule.dto.request.DateReqDto;
import com.clush.assignment.domain.schedule.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final QuerySchedulesService querySchedulesService;
    private final QueryAllSchedulesService queryAllSchedulesService;
    private final DeleteScheduleByIdService deleteScheduleByIdService;
    private final UpdateBookMarkService updateBookMarkService;
    private final QueryBookMarkService queryBookMarkService;

    @GetMapping
    public ResponseEntity<List<ScheduleResDto>> find(
            @RequestBody DateReqDto dateReqDto
    ) {
        return ResponseEntity.ok(querySchedulesService.execute(dateReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ScheduleResDto>> findAll() {
        return ResponseEntity.ok(queryAllSchedulesService.execute());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id
    ) {
        deleteScheduleByIdService.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/book-mark/{id}")
    public ResponseEntity<Void> toggleBookMark(
            @PathVariable("id") Long id
    ) {
        updateBookMarkService.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/book-mark")
    public ResponseEntity<List<ScheduleResDto>> findBookMark() {
        return ResponseEntity.ok(queryBookMarkService.execute());
    }
}
