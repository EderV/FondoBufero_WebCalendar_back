package com.fondo.bufero.WebCalendar.domain.ports.in;

import com.fondo.bufero.WebCalendar.domain.Event;

import java.util.UUID;

public interface EventServicePort {

    void saveEvent(Event event);

    void updateEvent(Event newEvent);

    void deleteEvent(Event event);

    Event findEventByUUID(UUID uuid);

}
