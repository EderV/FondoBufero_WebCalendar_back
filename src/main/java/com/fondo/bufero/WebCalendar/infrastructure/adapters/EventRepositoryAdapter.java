package com.fondo.bufero.WebCalendar.infrastructure.adapters;

import com.fondo.bufero.WebCalendar.domain.ports.out.EventRepositoryPort;
import com.fondo.bufero.WebCalendar.infrastructure.entity.EventEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
public class EventRepositoryAdapter implements EventRepositoryPort {


    @Override
    public void saveEvent(EventEntity event) {

    }

    @Override
    public void updateEvent(UUID oldEventUUID, EventEntity newEvent) {

    }

    @Override
    public void deleteEvent(EventEntity event) {

    }

    @Override
    public EventEntity findEventByUUID(UUID uuid) {
        return null;
    }

}
