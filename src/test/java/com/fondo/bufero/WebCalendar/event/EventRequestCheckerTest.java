package com.fondo.bufero.WebCalendar.event;

import com.fondo.bufero.WebCalendar.event.application.EventRequestCheckerDefault;
import com.fondo.bufero.WebCalendar.event.domain.Event;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class EventRequestCheckerTest {

    private final EventRequestCheckerDefault eventRequestCheckerDefault = new EventRequestCheckerDefault();

    @Test
    public void should_throw_exception_Event_null() {
        var exception = assertThrows(IllegalArgumentException.class, () -> eventRequestCheckerDefault.checkFullEvent(null));
        assertEquals("Input data is null", exception.getMessage());
    }

    @Test
    public void should_throw_exception_Event_dont_has_minimum_data() {
        var exception = new IllegalArgumentException();
        final var event = new Event();

        exception = assertThrows(IllegalArgumentException.class, () -> {
            eventRequestCheckerDefault.checkFullEvent(event);
        });
        assertEquals("Data provided in Event is not minimum required and/or is not correct. Event: " + event, exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            event.setTitle("Test title");
            eventRequestCheckerDefault.checkFullEvent(event);
        });
        assertEquals("Data provided in Event is not minimum required and/or is not correct. Event: " + event, exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            event.setTitle("Test title");
            event.setOwner("Test owner");
            eventRequestCheckerDefault.checkFullEvent(event);
        });
        assertEquals("Data provided in Event is not minimum required and/or is not correct. Event: " + event, exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            event.setTitle("Test title");
            event.setOwner("Test owner");
            event.setDate(new Date(3500, Calendar.NOVEMBER, 10, 12, 12));
            eventRequestCheckerDefault.checkFullEvent(event);
        });
        assertEquals("Data provided in Event is not minimum required and/or is not correct. Event: " + event, exception.getMessage());
    }

    @Test
    public void should_not_throw_exception_Event_has_minimum_data() {
        assertDoesNotThrow(() -> {
            var event = new Event();
            event.setTitle("Test title");
            event.setOwner("Test owner");
            event.setDate(new Date(3500, Calendar.NOVEMBER, 10, 12, 12));
            event.setDuration(10);
            eventRequestCheckerDefault.checkFullEvent(event);
        });
    }

    @Test
    public void should_throw_exception_Event_has_illegal_characters() {
        var exception = new IllegalArgumentException();
        final var event = new Event();

        exception = assertThrows(IllegalArgumentException.class, () -> {
            event.setTitle("Test title >");
            event.setDescription("Test description");
            event.setOwner("Test owner");
            event.setLogo("/logos/greensamilla.png");
            event.setDate(new Date(3500, Calendar.NOVEMBER, 10, 12, 12));
            event.setType("Test type");
            event.setLink("https://www.moonsama.com");
            event.setDuration(10);
            event.setCancelReason("Test canceled reason");
            eventRequestCheckerDefault.checkFullEvent(event);
        });
        assertEquals("Data provided contains illegal characters", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            event.setTitle("Test title");
            event.setDescription("Test description <");
            event.setOwner("Test owner");
            event.setLogo("/logos/greensamilla.png");
            event.setDate(new Date(3500, Calendar.NOVEMBER, 10, 12, 12));
            event.setType("Test type");
            event.setLink("https://www.moonsama.com");
            event.setDuration(10);
            event.setCancelReason("Test canceled reason");
            eventRequestCheckerDefault.checkFullEvent(event);
        });
        assertEquals("Data provided contains illegal characters", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            event.setTitle("Test title");
            event.setDescription("Test description");
            event.setOwner("Test owner <");
            event.setLogo("/logos/greensamilla.png");
            event.setDate(new Date(3500, Calendar.NOVEMBER, 10, 12, 12));
            event.setType("Test type");
            event.setLink("https://www.moonsama.com");
            event.setDuration(10);
            event.setCancelReason("Test canceled reason");
            eventRequestCheckerDefault.checkFullEvent(event);
        });
        assertEquals("Data provided contains illegal characters", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            event.setTitle("Test title");
            event.setDescription("Test description");
            event.setOwner("Test owner");
            event.setLogo("/logos/greensamilla.png \"");
            event.setDate(new Date(3500, Calendar.NOVEMBER, 10, 12, 12));
            event.setType("Test type");
            event.setLink("https://www.moonsama.com");
            event.setDuration(10);
            event.setCancelReason("Test canceled reason");
            eventRequestCheckerDefault.checkFullEvent(event);
        });
        assertEquals("Data provided contains illegal characters", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            event.setTitle("Test title");
            event.setDescription("Test description");
            event.setOwner("Test owner");
            event.setLogo("/logos/greensamilla.png");
            event.setDate(new Date(3500, Calendar.NOVEMBER, 10, 12, 12));
            event.setType("Test type '");
            event.setLink("https://www.moonsama.com");
            event.setDuration(10);
            event.setCancelReason("Test canceled reason");
            eventRequestCheckerDefault.checkFullEvent(event);
        });
        assertEquals("Data provided contains illegal characters", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            event.setTitle("Test title");
            event.setDescription("Test description");
            event.setOwner("Test owner");
            event.setLogo("/logos/greensamilla.png");
            event.setDate(new Date(3500, Calendar.NOVEMBER, 10, 12, 12));
            event.setType("Test type");
            event.setLink("https://www.moonsama.com ;");
            event.setDuration(10);
            event.setCancelReason("Test canceled reason");
            eventRequestCheckerDefault.checkFullEvent(event);
        });
        assertEquals("Data provided contains illegal characters", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            event.setTitle("Test title");
            event.setDescription("Test description");
            event.setOwner("Test owner");
            event.setLogo("/logos/greensamilla.png");
            event.setDate(new Date(3500, Calendar.NOVEMBER, 10, 12, 12));
            event.setType("Test type");
            event.setLink("https://www.moonsama.com");
            event.setDuration(10);
            event.setCancelReason("Test canceled reas;on");
            eventRequestCheckerDefault.checkFullEvent(event);
        });
        assertEquals("Data provided contains illegal characters", exception.getMessage());
    }

    @Test
    public void should_not_throw_exception_Event_doesnt_have_illegal_characters() {
        assertDoesNotThrow(() -> {
            var event = new Event();
            event.setTitle("Test title");
            event.setDescription("Test description");
            event.setOwner("Test owner");
            event.setLogo("/logos/greensamilla.png");
            event.setDate(new Date(3500, Calendar.NOVEMBER, 10, 12, 12));
            event.setType("Test type");
            event.setLink("https://www.moonsama.com");
            event.setDuration(10);
            event.setCancelReason("Test canceled reason");
        });
    }

    @Test
    public void should_throw_exception_Dates_are_null() {
        var exception = new IllegalArgumentException();

        exception = assertThrows(IllegalArgumentException.class, () -> eventRequestCheckerDefault.checkDate(null));
        assertEquals("Input data is null", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> eventRequestCheckerDefault.checkDate(null, new Date()));
        assertEquals("Input data is null", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> eventRequestCheckerDefault.checkDate(new Date(), null));
        assertEquals("Input data is null", exception.getMessage());
    }

    @Test
    public void should_throw_exception_Dates_are_incorrect() {
        var exception = new IllegalArgumentException();

        final var startDate = new Date(200);
        final var endDate = new Date(100);

        exception = assertThrows(IllegalArgumentException.class, () -> eventRequestCheckerDefault.checkDate(startDate, endDate));
        assertEquals("End date cannot be before start date. Start: " + startDate + " - End: " + endDate, exception.getMessage());
    }

    @Test
    public void should_not_throw_exception_Dates_ok() {
        assertDoesNotThrow(() -> {
            final var startDate = new Date(200);
            final var endDate = new Date(400);
            eventRequestCheckerDefault.checkDate(startDate);
            eventRequestCheckerDefault.checkDate(startDate, endDate);
        });
    }

    @Test
    public void should_throw_exception_Event_doesnt_have_id_for_delete() {
        var exception = new IllegalArgumentException();

        exception = assertThrows(IllegalArgumentException.class, () -> {
            final var event = new Event();
            eventRequestCheckerDefault.checkEventForDelete(event);
        });
        assertEquals("Id in event cannot be null for delete", exception.getMessage());
    }

    @Test
    public void should_not_throw_exception_Event_for_deletion_ok() {
        assertDoesNotThrow(() -> {
            final var event = new Event();
            event.setId(UUID.randomUUID());
            eventRequestCheckerDefault.checkEventForDelete(event);
        });
    }

}
