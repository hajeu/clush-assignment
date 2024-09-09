package com.clush.assignment.domain.schedule.controller;

import com.clush.assignment.domain.schedule.dto.request.BasicCalendarReqDto;
import com.clush.assignment.domain.schedule.service.calendar.CreateCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CreateCalendarService createCalendarService;

    @PostMapping
    public ResponseEntity<Void> create(
            @RequestBody BasicCalendarReqDto basicCalendarReqDto
    ) {
        createCalendarService.execute(basicCalendarReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
