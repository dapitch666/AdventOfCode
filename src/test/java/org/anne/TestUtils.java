package org.anne;

import org.anne.common.Day;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {

    public static void runTest(String year, String day, String part1, String part2) {
        String actualAnswer1 = "", actualAnswer2 = "";
        try {
            Day dayInstance = (Day) Class.forName("org.anne.aoc" + year + "." + day)
                    .getDeclaredConstructor().newInstance();
            dayInstance.execute();
            actualAnswer1 = dayInstance.getPart1();
            actualAnswer2 = dayInstance.getPart2();
        } catch (Exception e) {
            System.out.println("Error getting Year " + year + " " + day + " results: " + e.getMessage());
        }
        assertEquals(part1, actualAnswer1, "Year " + year + " " + day + " part1 failed");
        assertEquals(part2, actualAnswer2, "Year " + year + " " + day + " part2 failed");
        System.out.println("Year " + year + " " + day + " passed");
    }
}