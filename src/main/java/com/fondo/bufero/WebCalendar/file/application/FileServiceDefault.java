package com.fondo.bufero.WebCalendar.file.application;

import com.fondo.bufero.WebCalendar.file.domain.in.FileServicePort;
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
public class FileServiceDefault implements FileServicePort {

    private final FileStoragePort fileStoragePort;

    @Override
    public void saveLogo(MultipartFile multipartFile) throws IOException {
        fileStoragePort.saveFile(multipartFile, Paths.get("logos"));
    }

    @Override
    public Path getLogo(String filename) throws FileNotFoundException {
        return fileStoragePort.getFile(Paths.get("logos"), filename);
    }

    @Override
    public List<String> getAllLogosNames() throws IOException {
        return fileStoragePort.getAllFilenames(Paths.get("logos"));
    }

}
