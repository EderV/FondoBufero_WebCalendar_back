package com.fondo.bufero.WebCalendar.infrastructure.repository;

import com.fondo.bufero.WebCalendar.domain.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, UUID> {



}
