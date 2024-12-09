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
    void testChecksum() {
        Day9.block[] blocks = new Day9.block[5];
        blocks[0] = new Day9.block(1, 0);
        blocks[1] = new Day9.block(2, -1);
        blocks[2] = new Day9.block(3, 1);
        blocks[3] = new Day9.block(4, -1);
        blocks[4] = new Day9.block(5, 2);
        assertEquals(132, Day9.getChecksum(blocks));
    }

    @Test
    void testChecksumWithArrayOfBlocks() {
        Day9.block[] blocks = new Day9.block[16];
        blocks[0] = new Day9.block(2, 0);
        blocks[1] = new Day9.block(2, 9);
        blocks[2] = new Day9.block(1, 2);
        blocks[3] = new Day9.block(3, 1);
        blocks[4] = new Day9.block(3, 7);
        blocks[5] = new Day9.block(1, -1);
        blocks[6] = new Day9.block(2, 4);
        blocks[7] = new Day9.block(1, -1);
        blocks[8] = new Day9.block(3, 3);
        blocks[9] = new Day9.block(4, -1);
        blocks[10] = new Day9.block(4, 5);
        blocks[11] = new Day9.block(1, -1);
        blocks[12] = new Day9.block(4, 6);
        blocks[13] = new Day9.block(5, -1);
        blocks[14] = new Day9.block(4, 8);
        blocks[15] = new Day9.block(2, -1);
        assertEquals(2858, Day9.getChecksum(blocks));
    }

    @Test
    void testChecksumWithListOfBlocks() {
        ArrayList<Day9.block> blocks = new ArrayList<>();
        blocks.add(new Day9.block(2, 0));
        blocks.add(new Day9.block(2, 9));
        blocks.add(new Day9.block(1, 2));
        blocks.add(new Day9.block(3, 1));
        blocks.add(new Day9.block(3, 7));
        blocks.add(new Day9.block(1, -1));
        blocks.add(new Day9.block(2, 4));
        blocks.add(new Day9.block(1, -1));
        blocks.add(new Day9.block(3, 3));
        blocks.add(new Day9.block(4, -1));
        blocks.add(new Day9.block(4, 5));
        blocks.add(new Day9.block(1, -1));
        blocks.add(new Day9.block(4, 6));
        blocks.add(new Day9.block(5, -1));
        blocks.add(new Day9.block(4, 8));
        blocks.add(new Day9.block(2, -1));
        assertEquals(2858, Day9.getChecksum(blocks));
    }

    @Test
    void testChecksumWithListOfBlocksAddingNewZeroSizeFreeSpace() {
        ArrayList<Day9.block> blocks = new ArrayList<>();
        blocks.add(new Day9.block(2, 0));
        blocks.add(new Day9.block(2, 9));
        blocks.add(new Day9.block(1, 2));
        blocks.add(new Day9.block(3, 1));
        blocks.add(new Day9.block(3, 7));
        blocks.add(new Day9.block(1, -1));
        blocks.add(new Day9.block(0, -1));
        blocks.add(new Day9.block(2, 4));
        blocks.add(new Day9.block(1, -1));
        blocks.add(new Day9.block(3, 3));
        blocks.add(new Day9.block(4, -1));
        blocks.add(new Day9.block(4, 5));
        blocks.add(new Day9.block(1, -1));
        blocks.add(new Day9.block(4, 6));
        blocks.add(new Day9.block(5, -1));
        blocks.add(new Day9.block(4, 8));
        blocks.add(new Day9.block(2, -1));
        assertEquals(2858, Day9.getChecksum(blocks));
    }

}
