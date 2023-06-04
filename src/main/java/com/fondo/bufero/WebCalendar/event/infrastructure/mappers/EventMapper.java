package com.fondo.bufero.WebCalendar.event.infrastructure.mappers;

import com.fondo.bufero.WebCalendar.event.domain.Event;
import com.fondo.bufero.WebCalendar.event.infrastructure.dto.entity.EventEntity;
import com.fondo.bufero.WebCalendar.event.infrastructure.dto.request.EventRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventMapper MAPPER = Mappers.getMapper(EventMapper.class);

    EventRequest toEventRequest(Event event);
    Event toEvent(EventRequest eventRequest);
    Event toEvent(EventEntity eventEntity);
    EventEntity toEventEntity(Event event);

}
