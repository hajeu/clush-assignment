package com.clush.assignment.domain.schedule.controller;

import com.clush.assignment.domain.schedule.dto.request.CalendarReqDto;
import com.clush.assignment.domain.schedule.dto.request.DateReqDto;
import com.clush.assignment.domain.schedule.dto.response.CalendarResDto;
import com.clush.assignment.domain.schedule.service.calendar.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CreateCalendarService createCalendarsService;
    private final QueryCalendarsService queryCalendarsService;
    private final QueryAllCalendarsService queryAllCalendarsService;
    private final UpdateCalendarByIdService updateCalendarByIdService;

    @PostMapping
    public ResponseEntity<Void> create(
            @RequestBody CalendarReqDto calendarReqDto
    ) {
        createCalendarsService.execute(calendarReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CalendarResDto>> find(
            @RequestBody DateReqDto dateReqDto
    ) {
        return ResponseEntity.ok(queryCalendarsService.execute(dateReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CalendarResDto>> findAll() {
        return ResponseEntity.ok(queryAllCalendarsService.execute());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable("id") Long id,
            @RequestBody CalendarReqDto calendarReqDto
    ) {
        updateCalendarByIdService.execute(id, calendarReqDto);
        return ResponseEntity.noContent().build();
    }
}
