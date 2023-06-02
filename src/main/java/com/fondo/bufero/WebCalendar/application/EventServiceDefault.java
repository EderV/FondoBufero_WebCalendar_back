package com.fondo.bufero.WebCalendar.application;

import com.fondo.bufero.WebCalendar.domain.Event;
import com.fondo.bufero.WebCalendar.domain.ports.in.EventServicePort;
import com.fondo.bufero.WebCalendar.domain.ports.out.EventRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceDefault implements EventServicePort {

    private final EventRepositoryPort eventRepositoryPort;

    @Override
    public void saveEvent(Event event) {
        eventRepositoryPort.saveEvent(event);
    }

    @Override
    public void updateEvent(UUID oldEventUUID, Event newEvent) {
        eventRepositoryPort.updateEvent(oldEventUUID, newEvent);
    }

    @Override
    public void deleteEvent(Event event) {
        eventRepositoryPort.deleteEvent(event);
    }

    @Override
    public Event findEventByUUID(UUID uuid) {
        return eventRepositoryPort.findEventByUUID(uuid);
    }

}
