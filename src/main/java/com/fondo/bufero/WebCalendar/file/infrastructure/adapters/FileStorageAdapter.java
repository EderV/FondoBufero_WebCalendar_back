package com.fondo.bufero.WebCalendar.file.infrastructure.adapters;

import com.fondo.bufero.WebCalendar.file.domain.out.FileStoragePort;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileStorageAdapter implements FileStoragePort {

//    private final Path logosDir = Paths.get("logos");

    @Override
    public void saveFile(MultipartFile multipartFile, Path dir) throws IOException {
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }

        var inStream = multipartFile.getInputStream();
        var filePath = dir.resolve(multipartFile.getOriginalFilename());

        Files.copy(inStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public Path getFile(Path dir, String filename) throws FileNotFoundException {
        if (!Files.exists(dir)) {
            throw new FileNotFoundException("Directory " + dir + " not created yet");
        }

        var path = dir.resolve(filename);

        if (!Files.exists(path)) {
            throw new FileNotFoundException("Logo " + path + " not found");
        }

        return path;
    }

    @Override
    public List<Path> getAllFiles(Path dir) throws IOException {
        List<Path> fileList = new ArrayList<>();

        try (var stream = Files.newDirectoryStream(dir)) {
            for (Path file : stream) {
                if (Files.isRegularFile(file)) {
                    fileList.add(file);
                }
            }
        }

        return fileList;
    }

    @Override
    public List<String> getAllFilenames(Path dir) throws IOException {
        if (!Files.exists(dir)) {
            throw new FileNotFoundException("Directory " + dir + " not created yet");
        }

        var filenames = new ArrayList<String>();

        try (var stream = Files.newDirectoryStream(dir)) {
            for (Path file : stream) {
                if (Files.isRegularFile(file)) {
                    filenames.add(file.getFileName().toString());
                }
            }
        }

        return filenames;
    }

}
