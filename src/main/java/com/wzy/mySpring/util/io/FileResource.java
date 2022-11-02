package com.wzy.mySpring.util.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileResource implements Resource {

    private final String filePath;

    public FileResource(String path) {
        this.filePath = path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        Path path = new File(this.filePath).toPath();

        return Files.newInputStream(path);
    }
}
