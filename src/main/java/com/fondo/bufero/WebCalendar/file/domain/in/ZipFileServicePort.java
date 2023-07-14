package com.fondo.bufero.WebCalendar.file.domain.in;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;

public interface ZipFileServicePort {

    void createZip(List<Path> paths, OutputStream outputStream) throws IOException;

}
