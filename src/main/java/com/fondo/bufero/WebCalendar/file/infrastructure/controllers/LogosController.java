package com.fondo.bufero.WebCalendar.file.infrastructure.controllers;

import com.fondo.bufero.WebCalendar.file.domain.in.FileServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/logos")
@RequiredArgsConstructor
public class LogosController {

    private final FileServicePort fileServicePort;

    @GetMapping("/admin/logos/{filenames}")
    public ResponseEntity<?> getLogos(@PathVariable("filenames") List<String> filenames) {
        try {
            var path = fileServicePort.getLogo("ImagenPrueba.png");

            var resource = new FileSystemResource(path);
            var mediaType = MediaTypeFactory
                    .getMediaType(resource)
                    .orElse(MediaType.APPLICATION_OCTET_STREAM);

            var headers = new HttpHeaders();
            headers.setContentType(mediaType);

            var disposition = ContentDisposition
                    .inline() // or .attachment()
                    .filename(resource.getFilename())
                    .build();
            headers.setContentDisposition(disposition);
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.ok("");
    }

    @GetMapping("/admin/logos-list")
    public ResponseEntity<?> getLogos() {
        var filenames = new ArrayList<String>();
        try {
            filenames = (ArrayList<String>) fileServicePort.getAllLogosNames();
        } catch (IOException e) {
            return new ResponseEntity<>("Error obtainin logos names. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(filenames);
    }

    @PostMapping("/admin/upload-logo")
    public ResponseEntity<String> uploadLogo(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return new ResponseEntity<>("File is null or empty", HttpStatus.BAD_REQUEST);
        }

        try {
            fileServicePort.saveLogo(file);
        } catch (IOException e) {
            return new ResponseEntity<>("File cannot be saved. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("File " + file.getOriginalFilename() + " saved successfully");
    }

}
