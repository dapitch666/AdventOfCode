package org.anne.common;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

import static org.anne.common.Utils.repeat;
import static org.anne.common.Constants.*;

public class Year {
    private final int year;

    public Year(int year) {
        this.year = year;
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
                Day day = (Day) clazz.getDeclaredConstructor().newInstance();
                day.run();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                // Day is not implemented yet
            }
        }
    }

    private static void printTitle(String title, String color) {
        int length = title.length() + 6;
        String box = color + "+" + repeat("=", length) + "+" + ANSI_RESET;
        System.out.println(box);
        System.out.println(color + "||  " + ANSI_RESET + title + color + "  ||" + ANSI_RESET);
        System.out.println(box);
        System.out.println();
    }
}