package org.anne;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.anne.TestUtils.runTest;

public class ITFromFile {

    @ParameterizedTest//(name = "{0} {1} {2}")
    @CsvFileSource(resources = "/answers.csv", numLinesToSkip = 1)
    void testPuzzleAnswersFromFile(String year, String day, String part, String expectedAnswer) {
        runTest(year, day, part, expectedAnswer);
    }
}