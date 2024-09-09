package com.clush.assignment.domain.schedule.controller;

import com.clush.assignment.domain.schedule.service.DeleteScheduleByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final DeleteScheduleByIdService deleteScheduleByIdService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id
    ) {
        deleteScheduleByIdService.execute(id);
        return ResponseEntity.noContent().build();
    }
}
