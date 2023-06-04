package com.fondo.bufero.WebCalendar.event.application;

import com.fondo.bufero.WebCalendar.event.domain.Event;
import com.fondo.bufero.WebCalendar.event.domain.ports.in.EventRequestCheckerPort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class EventRequestCheckerDefault implements EventRequestCheckerPort {

    @Override
    public void checkFullEvent(Event event) throws IllegalArgumentException {
        nullCheck(event);
        checkMinimumDataRequired(event);
        checkIllegalCharacters(event);
    }

    @Override
    public void checkEventForDelete(Event event) throws IllegalArgumentException {
        nullCheck(event);
        if (event.getId() == null) {
            throw new IllegalArgumentException("Id in event cannot be null for delete");
        }
    }

    @Override
    public void checkDate(Date date) throws IllegalArgumentException {
        nullCheck(date);
    }

    @Override
    public void checkDate(Date startDate, Date endDate) throws IllegalArgumentException {
        nullCheck(startDate);
        nullCheck(endDate);

        if (endDate.before(startDate)) {
            throw new IllegalArgumentException(
                    "End date cannot be before start date. Start: " + startDate + " - End: " + endDate);
        }
    }

    private void nullCheck(Object object) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException("Input data is null");
        }
    }

    private void checkMinimumDataRequired(Event event) throws IllegalArgumentException {
        if (event.getTitle() == null || event.getTitle().isBlank() ||
            event.getOwner() == null || event.getOwner().isBlank() ||
            event.getDate() == null ||
            event.getDuration() == null || event.getDuration() < 0
        ) {
            throw new IllegalArgumentException(
                    "Data provided in Event is not minimum required and/or is not correct. Event: " + event);
        }
    }

    private void checkIllegalCharacters(Event event) throws IllegalArgumentException {
        if (findIllegalCharacters(event.getTitle()) ||
            findIllegalCharacters(event.getDescription()) ||
            findIllegalCharacters(event.getOwner()) ||
            findIllegalCharacters(event.getLogo()) ||
            findIllegalCharacters(event.getType()) ||
            findIllegalCharacters(event.getLink()) ||
            findIllegalCharacters(event.getCancelReason())
        ) {
            throw new IllegalArgumentException("Data provided contains illegal characters");
        }
    }

    private boolean findIllegalCharacters(String input) {
        if (input != null) {
            var pattern = "[<>\"';]";
            var regex = Pattern.compile(pattern);
            var matcher = regex.matcher(input);
            return matcher.find();
        }
        return false;
    }

}
