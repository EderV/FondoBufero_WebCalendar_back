package com.fondo.bufero.WebCalendar.file.infrastructure.controllers;

import com.fondo.bufero.WebCalendar.file.domain.in.LogosFilesServicePort;
import com.fondo.bufero.WebCalendar.file.domain.in.ZipFileServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/logos")
@RequiredArgsConstructor
public class LogosController {

    private final LogosFilesServicePort logosFilesServicePort;
    private final ZipFileServicePort zipFileServicePort;

    @GetMapping("/admin/logos/{filenames}")
    public ResponseEntity<?> getLogos(@PathVariable("filenames") List<String> filenames) {
        try {
            var path = logosFilesServicePort.getLogo("ImagenPrueba.png");

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
            var asdfasd = new ResponseEntity<>(resource, headers, HttpStatus.OK);
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.ok("");
    }

    @PostMapping(value = "/logos-zip", produces = "application/zip")
    public ResponseEntity<?> getLogosZip(@RequestBody List<String> logosNames) {
        var logosPaths = new ArrayList<Path>();
        for (var logoName : logosNames) {
            try {
                logosPaths.add(logosFilesServicePort.getLogo(logoName));
            } catch (FileNotFoundException ex) {
                log.error("File: " + logoName + " not found in logos directory");
            }
        }

        try {
            var zipBytes = zipFileServicePort.createZipAsByteArray(logosPaths);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=logos.zip")
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(zipBytes.length))
                    .body(zipBytes);
        } catch (IOException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/admin/logos-list")
    public ResponseEntity<?> getLogos() {
        var filenames = new ArrayList<String>();
        try {
            filenames = (ArrayList<String>) logosFilesServicePort.getAllLogosNames();
        } catch (IOException e) {
            return new ResponseEntity<>("Error obtaining logos names. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(filenames);
    }

    @PostMapping("/admin/upload-logo")
    public ResponseEntity<String> uploadLogo(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return new ResponseEntity<>("File is null or empty", HttpStatus.BAD_REQUEST);
        }

        try {
            logosFilesServicePort.saveLogo(file);
        } catch (IOException e) {
            return new ResponseEntity<>("File cannot be saved. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("File " + file.getOriginalFilename() + " saved successfully");
    }

}
