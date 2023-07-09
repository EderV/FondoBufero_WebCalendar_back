package com.fondo.bufero.WebCalendar.file.domain.out;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileStoragePort {

    void saveFile(MultipartFile file, Path dir) throws IOException;
    Path getFile(Path dir, String filename) throws FileNotFoundException;
    void getAllFiles(Path dir) throws IOException;
    List<String> getAllFilenames(Path dir) throws IOException;

}
