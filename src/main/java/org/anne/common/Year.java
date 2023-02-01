package org.anne.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

import static org.anne.common.Utils.repeat;

public class Year {
    private final int year;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_MAGENTA = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BRED = "\u001B[91m";
    public static final String ANSI_BGREEN = "\u001B[92m";
    public static final String ANSI_BYELLOW = "\u001B[93m";
    public static final String ANSI_BBLUE = "\u001B[94m";
    public static final String ANSI_BMAGENTA = "\u001B[95m";
    public static final String ANSI_BCYAN = "\u001B[96m";


    public Year(int year) {
        this.year = year;
    }


    private void executeClazz(Class<?> clazz) {
        try {
            final Method method = clazz.getMethod("main", String[].class);
            long start = System.nanoTime();
            method.invoke(null, (Object) null);
            long elapsed = System.nanoTime() - start;
            System.out.println(ANSI_BLUE + "Executed in " + elapsed / 1000000 + "ms" + ANSI_RESET);
            System.out.println();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String packageName = "org.anne.aoc" + this.year;
        var colors = List.of(ANSI_BLUE, ANSI_BBLUE, ANSI_BRED, ANSI_RED, ANSI_GREEN, ANSI_BGREEN,
                ANSI_YELLOW, ANSI_BYELLOW, ANSI_CYAN, ANSI_BCYAN, ANSI_MAGENTA, ANSI_BMAGENTA);
        Random random = new Random();
        int index = random.nextInt(colors.size());
        printTitle("Advent Of Code " + this.year, colors.get(index));

        for (int i = 1; i <= 25; i++) {
            try {
                Class<?> clazz = Class.forName(packageName + ".Day" + i);
                executeClazz(clazz);
            } catch (ClassNotFoundException e) {
                System.out.println("Day " + i + " not ready yet!");
            }
        }
    }

    private static void printTitle(String title, String color) {
        int length = title.length() + 4;
        String box = color + "+" + repeat("-", length) + "+" + ANSI_RESET;
        System.out.println(box);
        System.out.println(color + "|  " + ANSI_RESET + title + color + "  |" + ANSI_RESET);
        System.out.println(box);
    }
}
