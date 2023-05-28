package com.fondo.bufero.WebCalendar.application;

import com.fondo.bufero.WebCalendar.domain.EventEntity;
import com.fondo.bufero.WebCalendar.infrastructure.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final EventRepository eventRepository;

    @GetMapping
    public ResponseEntity<String> helloWorld() {

        eventRepository.save(EventEntity.builder()
                        .title("Evento moonsama mansion")
                        .description("asdgasdrfhgadfhadf")
                        .owner("Greensama")
                        .logo("/resources/logos/greensama.png")
                        .date(new Date(2023, Calendar.SEPTEMBER, 15, 16, 30))
                        .duration(120)
                        .type("Poker")
                        .link("https://www.greemsama.com")
                        .canceled(true)
                        .cancelReason("Testing event cancellation")
                        .build());

        return ResponseEntity.ok("Hello world");
    }

}
