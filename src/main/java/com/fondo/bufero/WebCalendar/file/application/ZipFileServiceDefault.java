package com.fondo.bufero.WebCalendar.file.application;

import com.fondo.bufero.WebCalendar.file.domain.in.ZipFileServicePort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ZipFileServiceDefault implements ZipFileServicePort {

    @Override
    public void createZip(List<Path> paths, OutputStream outputStream) throws IOException {
        try (var zipOutStream = new ZipOutputStream(outputStream)) {
            for (var path : paths) {
                addFileToZip(path, zipOutStream);
            }
        }
    }

    private void addFileToZip(Path file, ZipOutputStream zipOutputStream) throws IOException {
        byte[] bytes = Files.readAllBytes(file);
        ZipEntry zipEntry = new ZipEntry(file.getFileName().toString());
        zipOutputStream.putNextEntry(zipEntry);
        zipOutputStream.write(bytes);
        zipOutputStream.closeEntry();
    }

}
