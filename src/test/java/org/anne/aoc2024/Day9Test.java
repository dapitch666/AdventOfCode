package org.anne.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test {

    String input = "2333133121414131402";

    @Test
    void testPart1() {
        assertEquals(1928, Day9.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(2858, Day9.part2(input));
    }

   @Test
    void testChecksumWithListOfBlocks() {
        ArrayList<Day9.Block> blocks = new ArrayList<>();
        blocks.add(new Day9.Block(2, 0, 0, List.of(9, 9, 2)));
        blocks.add(new Day9.Block(3, 1, 0, List.of(7, 7, 7)));
        blocks.add(new Day9.Block(1, 0, 1, List.of(4, 4)));
        blocks.add(new Day9.Block(3, 3, 1));
        blocks.add(new Day9.Block(2, 0, 1));
        blocks.add(new Day9.Block(4, 5, 1));
        blocks.add(new Day9.Block(4, 6, 1));
        blocks.add(new Day9.Block(3, 0, 1));
        blocks.add(new Day9.Block(4, 8, 0));
        blocks.add(new Day9.Block(2, 0, 0));
        assertEquals(2858, Day9.getChecksum(blocks));
    }
}
