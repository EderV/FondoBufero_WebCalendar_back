package com.fondo.bufero.WebCalendar.event.domain.ports.in;

import com.fondo.bufero.WebCalendar.event.domain.Event;
import com.fondo.bufero.WebCalendar.event.domain.exceptions.EventNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface EventServicePort {

    void saveEvent(Event event);

    void updateEvent(Event newEvent);

    void deleteEvent(Event event);

    Event findEventByUUID(UUID uuid) throws EventNotFoundException;

    List<Event> findEventsBetweenDates(Date startDate, Date endDate);

}
