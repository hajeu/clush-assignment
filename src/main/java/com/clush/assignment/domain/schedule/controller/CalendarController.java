package com.clush.assignment.domain.schedule.controller;

import com.clush.assignment.domain.schedule.dto.request.BasicCalendarReqDto;
import com.clush.assignment.domain.schedule.dto.request.DateReqDto;
import com.clush.assignment.domain.schedule.dto.response.BasicCalendarResDto;
import com.clush.assignment.domain.schedule.service.calendar.CreateCalendarService;
import com.clush.assignment.domain.schedule.service.calendar.QueryAllCalendarsService;
import com.clush.assignment.domain.schedule.service.calendar.QueryCalendarsService;
import com.clush.assignment.domain.schedule.service.calendar.UpdateCalendarByIdService;
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
            @RequestBody BasicCalendarReqDto basicCalendarReqDto
    ) {
        createCalendarsService.execute(basicCalendarReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<BasicCalendarResDto>> find(
            @RequestBody DateReqDto dateReqDto
    ) {
        return ResponseEntity.ok(queryCalendarsService.execute(dateReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BasicCalendarResDto>> findAll() {
        return ResponseEntity.ok(queryAllCalendarsService.execute());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable("id") Long id,
            @RequestBody BasicCalendarReqDto basicCalendarReqDto
    ) {
        updateCalendarByIdService.execute(id, basicCalendarReqDto);
        return ResponseEntity.noContent().build();
    }
}
