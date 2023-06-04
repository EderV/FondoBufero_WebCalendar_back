package com.fondo.bufero.WebCalendar.event.domain.ports.out;

import com.fondo.bufero.WebCalendar.event.domain.Event;

import java.util.UUID;

public interface EventRepositoryPort {

    void saveEvent(Event event);

    void updateEvent(Event newEvent);

    void deleteEvent(Event event);

    Event findEventByUUID(UUID uuid);

}
