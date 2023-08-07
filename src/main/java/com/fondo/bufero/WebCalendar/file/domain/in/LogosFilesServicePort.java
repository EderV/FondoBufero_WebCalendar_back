package com.fondo.bufero.WebCalendar.file.domain.in;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface LogosFilesServicePort {

    void saveLogo(MultipartFile multipartFile) throws IOException;

    Path getLogo(String filename) throws FileNotFoundException;

    List<String> getAllLogosNames() throws IOException;

}
