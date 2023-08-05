package com.fondo.bufero.WebCalendar.file.domain.in;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;

public interface ZipFileServicePort {

    byte[] createZipAsByteArray(List<Path> paths) throws IOException;

}
