package org.anne.aoc2022;

import org.anne.common.Utils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {
    final List<String> input = Arrays.asList(
            "30373",
            "25512",
            "65332",
            "33549",
            "35390"
    );

    @Test
    void part1() {
        assertEquals(21, Day8.part1(input));
    }

    @Test
    void part2() {
        assertEquals(8, Day8.part2(input));
    }

    @Test
    void testTranspose() {
        assertEquals(Arrays.asList(
                "32633",
                "05535",
                "35353",
                "71349",
                "32290"
        ), Utils.transpose(input));

        assertEquals(Arrays.asList(
                "ADF",
                "BEG",
                "C H"
        ), Utils.transpose(Arrays.asList(
                "ABC",
                "DE",
                "FGH"
        )));
    }
}