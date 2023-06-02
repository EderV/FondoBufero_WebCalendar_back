package com.fondo.bufero.WebCalendar.infrastructure.controller;

import com.fondo.bufero.WebCalendar.domain.Event;
import com.fondo.bufero.WebCalendar.domain.ports.in.EventRequestCheckerPort;
import com.fondo.bufero.WebCalendar.domain.ports.in.EventServicePort;
import com.fondo.bufero.WebCalendar.infrastructure.dto.entity.EventEntity;
import com.fondo.bufero.WebCalendar.infrastructure.dto.request.EventRequest;
import com.fondo.bufero.WebCalendar.infrastructure.mappers.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {

    private final EventRequestCheckerPort eventRequestCheckerPort;
    private final EventServicePort eventServicePort;

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("EventController ok");
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody EventRequest eventRequest) {
        var event = toEvent(eventRequest);
        eventServicePort.saveEvent(event);
        return ResponseEntity.ok("");
    }

    private Event toEvent(EventRequest eventRequest) {
        return EventMapper.MAPPER.toEvent(eventRequest);
    }

}
