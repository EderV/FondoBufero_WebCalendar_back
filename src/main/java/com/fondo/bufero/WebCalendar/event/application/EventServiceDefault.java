package com.fondo.bufero.WebCalendar.event.application;

import com.fondo.bufero.WebCalendar.event.domain.Event;
import com.fondo.bufero.WebCalendar.event.domain.ports.in.EventServicePort;
import com.fondo.bufero.WebCalendar.event.domain.ports.out.EventRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
    public void updateEvent(Event newEvent) {
        eventRepositoryPort.updateEvent(newEvent);
    }

    @Override
    public void deleteEvent(Event event) {
        eventRepositoryPort.deleteEvent(event);
    }

    @Override
    public Event findEventByUUID(UUID uuid) {
        return eventRepositoryPort.findEventByUUID(uuid);
    }

    @Override
    public List<Event> findEventsBetweenDates(Date startDate, Date endDate) {
        return eventRepositoryPort.findEventsBetweenDates(startDate, endDate);
    }

}
