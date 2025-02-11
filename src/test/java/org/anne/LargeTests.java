package org.anne;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.anne.TestUtils.runTest;

public class LargeTests {

    @ParameterizedTest(name = "{0} {1} {2}")
    @CsvSource({
            "2019, Day5, part1, 14155342",
            "2019, Day5, part2, 8684145",
            "2021, Day15, part1, 462",
            "2021, Day15, part2, 2846",
    })
    void testPuzzleAnswers(String year, String day, String part, String expectedAnswer) {
        runTest(year, day, part, expectedAnswer);
    }
}