package com.fondo.bufero.WebCalendar.file.infrastructure.controllers;

import com.fondo.bufero.WebCalendar.file.domain.in.FileServicePort;
import com.fondo.bufero.WebCalendar.file.domain.in.ZipFileServicePort;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@RestController
@RequestMapping("/api/logos")
@RequiredArgsConstructor
public class LogosController {

    private final FileServicePort fileServicePort;
    private final ZipFileServicePort zipFileServicePort;

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
            var asdfasd = new ResponseEntity<>(resource, headers, HttpStatus.OK);
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.ok("");
    }

    @GetMapping(value = "/admin/logos/multiple", produces = "application/zip")
    public ResponseEntity<String> getLogosMultiple(HttpServletResponse response) {
        try {
            var path1 = fileServicePort.getLogo("ImagenPrueba.png");
            var path2 = fileServicePort.getLogo("Captura.png");
            var path3 = fileServicePort.getLogo("Spring-Logo.png");

//            zipFileServicePort.createZip(Arrays.asList(path1, path2, path3), response.getOutputStream());
        } catch (IOException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("");

//        var disposition = ContentDisposition
//                .inline() // or .attachment()
//                .filename("images.zip")
//                .build();
//
//        var headers = new HttpHeaders();
//        headers.setContentDisposition(disposition);
//        response.setStatus(HttpServletResponse.SC_OK);
//        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=images.zip");
    }

    @PostMapping(value = "/logos-zip", produces = "application/zip")
    public ResponseEntity<?> getLogosZip(@RequestBody List<String> logosNames) {
        var logosPaths = new ArrayList<Path>();
        for (var logoName : logosNames) {
            try {
                logosPaths.add(fileServicePort.getLogo(logoName));
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
            filenames = (ArrayList<String>) fileServicePort.getAllLogosNames();
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
            fileServicePort.saveLogo(file);
        } catch (IOException e) {
            return new ResponseEntity<>("File cannot be saved. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("File " + file.getOriginalFilename() + " saved successfully");
    }

}
