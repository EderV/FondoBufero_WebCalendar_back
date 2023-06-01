package com.fondo.bufero.WebCalendar.application;

import com.fondo.bufero.WebCalendar.domain.Event;
import com.fondo.bufero.WebCalendar.domain.ports.in.EventServicePort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventServiceDefault implements EventServicePort {

    @Override
    public void saveEvent(Event event) {

    }

    @Override
    public void updateEvent(UUID oldEventUUID, Event newEvent) {

    }

    @Override
    public void deleteEvent(Event event) {

    }

    @Override
    public Event findEventByUUID(UUID uuid) {
        return null;
    }

}
