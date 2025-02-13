package org.anne;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.anne.TestUtils.runTest;

public class LargeTests {

    @ParameterizedTest(name = "{0} {1} {2}")
    @CsvSource({
            "2015, Day1, part1, 232",
            "2015, Day1, part2, 1783",
            "2015, Day2, part1, 1598415",
            "2015, Day2, part2, 3812909",
    })
    void testPuzzleAnswers(String year, String day, String part, String expectedAnswer) {
        runTest(year, day, part, expectedAnswer);
    }
}