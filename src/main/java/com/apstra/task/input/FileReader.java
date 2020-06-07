package com.apstra.task.input;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

    private FileReader() {
    }

    public static List<String> read(String fileName) {
        String filePath = Objects.requireNonNull(FileReader.class.getClassLoader().getResource(fileName)).getPath();
        try (Stream<String> file = Files.lines(Paths.get(filePath))) {
            return file.collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
