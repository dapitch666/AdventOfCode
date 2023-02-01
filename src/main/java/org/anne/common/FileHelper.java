package org.anne.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        try {
            return Files.readAllLines(inputPath).stream().map(Integer::parseInt).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.format("There was an Error reading the File: %s%n", e);
            return new ArrayList<>();
        }
    }

    public static String readFileOneLine(Path inputPath) {
        try {
            return Files.readAllLines(inputPath).get(0);
        } catch (IOException | NumberFormatException e) {
            System.err.format("There was an Error reading the File: %s%n", e);
            return "";
        }
    }

    public static List<Integer> readFileIntegerOneLine(Path inputPath) {
        try {
            return Arrays.stream(Files.readAllLines(inputPath).get(0).split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException | NumberFormatException e) {
            System.err.format("There was an Error reading the File: %s%n", e);
            return new ArrayList<>();
        }
    }

    public static List<Long> readFileAsLongs(Path inputPath) {
        try {
            return Files.readAllLines(inputPath).stream().map(Long::parseLong).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.format("There was an Error reading the File: %s%n", e);
            return new ArrayList<>();
        }
    }
}
