package org.anne;

import org.anne.common.Day;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {

    public static void runTest(String year, String day, String part, String expectedAnswer) {
        String actualAnswer = "";
        try {
            Day dayInstance = (Day) Class.forName("org.anne.aoc" + year + "." + day)
                    .getDeclaredConstructor().newInstance();
            dayInstance.execute();
            actualAnswer = part.equals("part1") ? dayInstance.getPart1() : dayInstance.getPart2();
        } catch (Exception e) {
            System.out.println("Day " + day + " is not implemented yet");
        }
        assertEquals(expectedAnswer, actualAnswer, "Year " + year + " " + day + " " + part + " failed");
        System.out.println("Year " + year + " " + day + " " + part + " passed");
    }
}