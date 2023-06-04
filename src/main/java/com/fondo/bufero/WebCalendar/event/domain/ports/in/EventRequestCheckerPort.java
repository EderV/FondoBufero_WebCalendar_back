package com.fondo.bufero.WebCalendar.event.domain.ports.in;

import com.fondo.bufero.WebCalendar.event.domain.Event;

import java.util.Date;

public interface EventRequestCheckerPort {

    void checkFullEvent(Event event) throws IllegalArgumentException;

    void checkEventForDelete(Event event) throws IllegalArgumentException;

    void checkDate(Date date) throws IllegalArgumentException;

    void checkDate(Date startDate, Date endDate) throws IllegalArgumentException;

}
