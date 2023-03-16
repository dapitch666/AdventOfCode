package org.anne.aoc2019;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {

    @Test
    void part1() {
        Computer computer = new Computer("1002,4,3,4,33");
        computer.compute();
        assertEquals(99, computer.getIntCode()[4]);
    }

    @Test
    void part2() {
        assertEquals(999, Day5.part2("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99"));
    }
}