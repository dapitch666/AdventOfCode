package org.anne.aoc2017;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day20Test {

    List<String> input = Arrays.asList(
            "p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>",
            "p=<4,0,0>, v=<0,0,0>, a=<-2,0,0>"
    );

    List<String> input2 = Arrays.asList(
            "p=<-6,0,0>, v=<3,0,0>, a=<0,0,0>",
            "p=<-4,0,0>, v=<2,0,0>, a=<0,0,0>",
            "p=<-2,0,0>, v=<1,0,0>, a=<0,0,0>",
            "p=<3,0,0>, v=<1,0,0>, a=<0,0,0>"
    );

    @Test
    void testPart1() {
        assertEquals(0, Day20.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(1, Day20.part2(input2));
    }
}
