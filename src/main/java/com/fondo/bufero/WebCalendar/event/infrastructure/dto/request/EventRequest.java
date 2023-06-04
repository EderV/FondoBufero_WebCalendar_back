package com.fondo.bufero.WebCalendar.event.infrastructure.dto.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    private UUID id;

    private String title;

    private String description;

    private String owner;

    private String logo;

    private Date date;

    private Integer duration;   // In minutes

    private String type;

    private String link;

    private Boolean canceled;

    private String cancelReason;

    private Date createdAt;

}
