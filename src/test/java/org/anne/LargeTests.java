package org.anne;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.anne.TestUtils.runTest;

public class LargeTests {

    @ParameterizedTest(name = "{0} {1} {2}")
    @CsvSource({
            "2015, Day7, part1, 46065",
            "2015, Day7, part2, 14134",
    })
    void testPuzzleAnswers(String year, String day, String part, String expectedAnswer) {
        runTest(year, day, part, expectedAnswer);
    }
}