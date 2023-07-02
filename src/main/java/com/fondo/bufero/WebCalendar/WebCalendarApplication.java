package com.fondo.bufero.WebCalendar;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.TimeZone;

@SpringBootApplication
public class WebCalendarApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebCalendarApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(WebCalendarApplication.class, args);
	}

	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

}
