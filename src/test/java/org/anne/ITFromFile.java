package org.anne;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.anne.TestUtils.runTest;

public class ITFromFile {

    @ParameterizedTest(name = "{0} {1}")
    @CsvFileSource(resources = "/answers.csv", numLinesToSkip = 1)
    void testPuzzleAnswersFromFile(String year, String day, String part1, String part2) {
        runTest(year, day, part1, part2);
    }
}