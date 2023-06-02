package com.fondo.bufero.WebCalendar.domain.ports.out;

import com.fondo.bufero.WebCalendar.domain.Event;

import java.util.UUID;

public interface EventRepositoryPort {

    void saveEvent(Event event);

    void updateEvent(UUID oldEventUUID, Event newEvent);

    void deleteEvent(Event event);

    Event findEventByUUID(UUID uuid);

}
