package org.anne;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.anne.TestUtils.runTest;

public class LargeTests {

    @ParameterizedTest(name = "{0} {1} {2}")
    @CsvSource({
            "2015, Day7, 46065, 14134",
    })
    void testPuzzleAnswers(String year, String day, String part1, String part2) {
        runTest(year, day, part1, part2);
    }
}