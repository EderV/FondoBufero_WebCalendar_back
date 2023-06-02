package com.fondo.bufero.WebCalendar.infrastructure.adapters;

import com.fondo.bufero.WebCalendar.domain.Event;
import com.fondo.bufero.WebCalendar.domain.ports.out.EventRepositoryPort;
import com.fondo.bufero.WebCalendar.infrastructure.dto.entity.EventEntity;
import com.fondo.bufero.WebCalendar.infrastructure.mappers.EventMapper;
import com.fondo.bufero.WebCalendar.infrastructure.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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

    private EventEntity toEventEntity(Event event) {
        return EventMapper.MAPPER.toEventEntity(event);
    }

    private Event toEvent(EventEntity eventEntity) {
        return EventMapper.MAPPER.toEvent(eventEntity);
    }

}
