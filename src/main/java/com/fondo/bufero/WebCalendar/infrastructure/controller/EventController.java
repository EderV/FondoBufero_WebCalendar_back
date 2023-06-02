package com.fondo.bufero.WebCalendar.infrastructure.controller;

import com.fondo.bufero.WebCalendar.domain.Event;
import com.fondo.bufero.WebCalendar.domain.ports.in.EventRequestCheckerPort;
import com.fondo.bufero.WebCalendar.domain.ports.in.EventServicePort;
import com.fondo.bufero.WebCalendar.infrastructure.dto.request.EventRequest;
import com.fondo.bufero.WebCalendar.infrastructure.mappers.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getEventByUUID(@PathVariable(name = "uuid") UUID uuid) {
        var event = eventServicePort.findEventByUUID(uuid);
        if (event != null) {
            var eventRequest = toEventRequest(event);
            return new ResponseEntity<>(eventRequest, HttpStatus.OK);
        }
        return new ResponseEntity<>("Event with uuid: " + uuid.toString() + " not found", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{start_date}/{end_date}")
    public ResponseEntity<?> getEventBetweenDates(
            @PathVariable(name = "start_date") String startDate,
            @PathVariable(name = "end_date") String endDate
    ) {
        return new ResponseEntity<>("Not implemented", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody EventRequest eventRequest) {
        var event = toEvent(eventRequest);
        eventServicePort.saveEvent(event);
        return ResponseEntity.ok("");
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody EventRequest eventRequest) {
        var event = toEvent(eventRequest);
        eventServicePort.updateEvent(event);
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody EventRequest eventRequest) {
        var event = toEvent(eventRequest);
        eventServicePort.deleteEvent(event);
        return ResponseEntity.ok("");
    }

    private Event toEvent(EventRequest eventRequest) {
        return EventMapper.MAPPER.toEvent(eventRequest);
    }

    private EventRequest toEventRequest(Event event) {
        return EventMapper.MAPPER.toEventRequest(event);
    }

}
