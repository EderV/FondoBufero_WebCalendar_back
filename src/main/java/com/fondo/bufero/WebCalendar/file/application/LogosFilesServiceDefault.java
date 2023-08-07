package com.fondo.bufero.WebCalendar.file.application;

import com.fondo.bufero.WebCalendar.file.domain.in.LogosFilesServicePort;
import com.fondo.bufero.WebCalendar.file.domain.out.FileStoragePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LogosFilesServiceDefault implements LogosFilesServicePort {

    private final FileStoragePort fileStoragePort;

    private final String logosDirectory = "logos";

    @Override
    public void saveLogo(MultipartFile multipartFile) throws IOException {
        fileStoragePort.saveFile(multipartFile, Paths.get(logosDirectory));
    }

    @Override
    public Path getLogo(String filename) throws FileNotFoundException {
        return fileStoragePort.getFile(Paths.get(logosDirectory), filename);
    }

    @Override
    public List<String> getAllLogosNames() throws IOException {
        return fileStoragePort.getAllFilenames(Paths.get(logosDirectory));
    }

}
