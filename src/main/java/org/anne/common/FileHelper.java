package org.anne.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileHelper {

    public static List<String> readFile(Path inputPath) {
        try {
            return Files.readAllLines(inputPath);
        } catch (IOException e) {
            System.err.format("There was an Error reading the File: %s%n", e);
            return new ArrayList<>();
        }
    }

    public static List<Integer> readFileAsInts(Path inputPath) {
        return readFile(inputPath).stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    public static String readFileOneLine(Path inputPath) {
        return readFile(inputPath).getFirst();
    }

    public static List<Integer> readFileIntegerOneLine(Path inputPath, String delimiter) {
        return Arrays.stream(readFile(inputPath).getFirst().split(delimiter))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
    }

    public static List<Long> readFileAsLongs(Path inputPath) {
        return readFile(inputPath).stream().map(Long::parseLong).collect(Collectors.toList());
    }

    public static List<Integer> readFileGetAllInts(Path inputPath) {
        return readFile(inputPath).stream()
                    .flatMap(s -> Pattern.compile("\\d+").matcher(s).results().map(m -> Integer.parseInt(m.group())))
                    .collect(Collectors.toList());
    }
}
