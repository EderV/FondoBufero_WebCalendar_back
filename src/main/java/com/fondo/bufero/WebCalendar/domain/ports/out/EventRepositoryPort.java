package com.fondo.bufero.WebCalendar.domain.ports.out;

import com.fondo.bufero.WebCalendar.infrastructure.entity.EventEntity;

import java.util.UUID;

public interface EventRepositoryPort {

    void saveEvent(EventEntity event);

    void updateEvent(UUID oldEventUUID, EventEntity newEvent);

    void deleteEvent(EventEntity event);

    EventEntity findEventByUUID(UUID uuid);

}
