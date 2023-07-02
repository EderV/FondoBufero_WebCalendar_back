package com.fondo.bufero.WebCalendar.event.infrastructure.adapters;

import com.fondo.bufero.WebCalendar.event.domain.Event;
import com.fondo.bufero.WebCalendar.event.domain.ports.out.EventRepositoryPort;
import com.fondo.bufero.WebCalendar.event.infrastructure.dto.entity.EventEntity;
import com.fondo.bufero.WebCalendar.event.infrastructure.mappers.EventMapper;
import com.fondo.bufero.WebCalendar.event.infrastructure.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EventRepositoryAdapter implements EventRepositoryPort {

    private final EventRepository eventRepository;

    @Override
    public void saveEvent(Event event) {
        var eventEntity = toEventEntity(event);
        eventRepository.save(eventEntity);
    }

    @Override
    @Transactional
    public void updateEvent(Event newEvent) {
        var newEventEntity = toEventEntity(newEvent);
        eventRepository.updateEvent(newEventEntity);
    }

    @Override
    public void deleteEvent(Event event) {
        var eventEntity = toEventEntity(event);
        eventRepository.delete(eventEntity);
    }

    @Override
    public Event findEventByUUID(UUID uuid) {
        var event = eventRepository.findEventByUUID(uuid);
        return event.map(this::toEvent).orElse(null);
    }

    @Override
    public List<Event> findEventsBetweenDates(Date startDate, Date endDate) {
        var events = eventRepository.findEventsBetweenDates(startDate, endDate);
        return events.map(this::toListEvent).orElse(new ArrayList<>());
    }

    private EventEntity toEventEntity(Event event) {
        return EventMapper.MAPPER.toEventEntity(event);
    }

    private Event toEvent(EventEntity eventEntity) {
        return EventMapper.MAPPER.toEvent(eventEntity);
    }

    private List<Event> toListEvent(List<EventEntity> eventEntities) {
        var events = new ArrayList<Event>();
        for (var event : eventEntities) {
            events.add(toEvent(event));
        }
        return events;
    }

}
