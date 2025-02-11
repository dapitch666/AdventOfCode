package org.anne.common;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.anne.common.ClazzHelper.*;
import static org.anne.common.Constants.*;
import static org.anne.common.Utils.*;

public abstract class Day {
    protected final int year;
    protected final int day;
    private Object part1;
    private Object part2;
    private String name;

    public Day() {
        Class<?> clazz = this.getClass();
        this.year = getYearFromClass(clazz);
        this.day = getDayNumberFromClass(clazz);
    }

    public void run() {
        long start = System.nanoTime();
        execute();
        long elapsed = System.nanoTime() - start;
        printParts();
        System.out.println(ANSI_BLUE + "Executed in " + elapsed / 1000000 + "ms" + ANSI_RESET);
        System.out.println();
    }

    public abstract void execute();

    public void setPart1(Object part1) {
        this.part1 = part1;
    }

    public void setPart2(Object part2) {
        this.part2 = part2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printPart(int partNumber) {
        String result = (partNumber == 1 ? part1 : part2).toString();
        if (containsNewLine(result)) {
            System.out.println("Part " + partNumber + ":" + LINE_SEPARATOR + result);
        } else {
            System.out.println("Part " + partNumber + ": " + result);
        }
    }

    public String getPart1() {
        return part1.toString();
    }

    public String getPart2() {
        return part2.toString();
    }

    public void printParts() {
        String title = " Day " + this.day + ": " + this.name;
        System.out.println(title);
        System.out.println(repeat("=", title.length() + 1));
        printPart(1);
        printPart(2);
    }

    protected Path getPath() {
        return Paths.get(String.format(FILE_PATH, this.year, this.day));
    }

    public List<String> readFile() {
        return FileHelper.readFile(getPath());
    }

    public String readFileOneLine() {
        return FileHelper.readFileOneLine(getPath());
    }

    public List<Integer> readFileAsInts() {
        return FileHelper.readFileAsInts(getPath());
    }

    public List<Integer> readFileIntegerOneLine(String delimiter) {
        return FileHelper.readFileIntegerOneLine(getPath(), delimiter);
    }

    public List<Long> readFileAsLongs() {
        return FileHelper.readFileAsLongs(getPath());
    }

    public List<Integer> readFileGetAllInts() {
        return FileHelper.readFileGetAllInts(getPath());
    }
}