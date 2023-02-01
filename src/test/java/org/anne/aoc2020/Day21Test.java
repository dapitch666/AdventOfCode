package org.anne.aoc2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day21Test {

    List<String> input = List.of(
            "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
            "trh fvjkl sbzzf mxmxvkd (contains dairy)",
            "sqjhc fvjkl (contains soy)",
            "sqjhc mxmxvkd sbzzf (contains fish)"
    );

    @Test
    void part1() {
        assertEquals(5, Day21.part1(input));
    }

    @Test
    void part2() {
        assertEquals("mxmxvkd,sqjhc,fvjkl", Day21.part2(input));
    }
}