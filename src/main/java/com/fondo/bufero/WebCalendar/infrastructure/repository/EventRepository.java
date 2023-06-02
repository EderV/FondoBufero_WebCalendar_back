package com.fondo.bufero.WebCalendar.infrastructure.repository;

import com.fondo.bufero.WebCalendar.infrastructure.dto.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, UUID> {

    @Query("SELECT e FROM EventEntity e WHERE e.id = ?1")
    Optional<EventEntity> findEventByUUID(UUID eventUUID);

    @Modifying
    @Query("UPDATE EventEntity e " +
            "SET " +
            "e.title = ?#{#event.title}, " +
            "e.description = ?#{#event.description}, " +
            "e.date = ?#{#event.date}, " +
            "e.duration = ?#{#event.duration}, " +
            "e.link = ?#{#event.link}, " +
            "e.canceled = ?#{#event.canceled}, " +
            "e.cancelReason = ?#{#event.cancelReason} " +
            "WHERE e.id = ?#{#event.id}")
    void updateEvent(EventEntity event);

}
