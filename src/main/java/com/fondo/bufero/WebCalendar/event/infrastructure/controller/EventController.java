package com.fondo.bufero.WebCalendar.event.infrastructure.controller;

import com.fondo.bufero.WebCalendar.event.domain.Event;
import com.fondo.bufero.WebCalendar.event.domain.exceptions.EventNotFoundException;
import com.fondo.bufero.WebCalendar.event.domain.ports.in.EventRequestCheckerPort;
import com.fondo.bufero.WebCalendar.event.domain.ports.in.EventServicePort;
import com.fondo.bufero.WebCalendar.event.infrastructure.dto.request.EventRequest;
import com.fondo.bufero.WebCalendar.event.infrastructure.mappers.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public ResponseEntity<?> getEventByUUID(@PathVariable(name = "uuid") UUID uuid) throws EventNotFoundException {
        var event = eventServicePort.findEventByUUID(uuid);
//        if (event != null) {
            var eventRequest = toEventRequest(event);
            return new ResponseEntity<>(eventRequest, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(
//                "Event with uuid: " + uuid.toString() + " not found", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{start_date}/{end_date}")
    public ResponseEntity<?> getEventBetweenDates(
            @PathVariable(name = "start_date") @DateTimeFormat(pattern = "dd-MM-yyyy_HH:mm:ss") Date startDate,
            @PathVariable(name = "end_date") @DateTimeFormat(pattern = "dd-MM-yyyy_HH:mm:ss") Date endDate
    ) throws IllegalArgumentException {

        eventRequestCheckerPort.checkDate(startDate, endDate);

        var events = eventServicePort.findEventsBetweenDates(startDate, endDate);
        if (!events.isEmpty()) {
            var eventRequests = toListEventRequest(events);
            return new ResponseEntity<>(eventRequests, HttpStatus.OK);
        }

        return new ResponseEntity<>(
                "There are no events between dates " + startDate + " - " + endDate, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/admin/save")
    public ResponseEntity<String> save(@RequestBody EventRequest eventRequest) throws IllegalArgumentException {
        var event = toEvent(eventRequest);

        eventRequestCheckerPort.checkFullEvent(event);

        eventServicePort.saveEvent(event);
        return ResponseEntity.ok("");
    }

    @PutMapping("/admin/update")
    public ResponseEntity<String> update(@RequestBody EventRequest eventRequest) throws IllegalArgumentException {
        var event = toEvent(eventRequest);

        eventRequestCheckerPort.checkFullEvent(event);

        eventServicePort.updateEvent(event);
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/admin/delete")
    public ResponseEntity<String> delete(@RequestBody EventRequest eventRequest) throws IllegalArgumentException {
        var event = toEvent(eventRequest);

        eventRequestCheckerPort.checkEventForDelete(event);

        eventServicePort.deleteEvent(event);
        return ResponseEntity.ok("");
    }

    private Event toEvent(EventRequest eventRequest) {
        return EventMapper.MAPPER.toEvent(eventRequest);
    }

    private EventRequest toEventRequest(Event event) {
        return EventMapper.MAPPER.toEventRequest(event);
    }

    private List<EventRequest> toListEventRequest(List<Event> events) {
        var eventRequests = new ArrayList<EventRequest>();
        for (var event : events) {
            eventRequests.add(toEventRequest(event));
        }
        return eventRequests;
    }

}
