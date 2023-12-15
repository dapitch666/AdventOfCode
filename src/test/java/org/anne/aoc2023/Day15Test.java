package org.anne.aoc2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day15Test {

    String input = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7";
    
    @Test
    void testPart1() {
        assertEquals(1320, Day15.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(145, Day15.part2(input));
    }
    
    @Test
    void testHash() {
        assertEquals(52, Day15.hash("HASH"));
        assertEquals(30, Day15.hash("rn=1"));
    }
 }
