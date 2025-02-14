package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {

    @Test
    void testPart1() {
        assertEquals(6, Day12.part1("[1,2,3]"));
        assertEquals(6, Day12.part1("{\"a\":2,\"b\":4}"));
        assertEquals(3, Day12.part1("[[[3]]]"));
        assertEquals(3, Day12.part1("{\"a\":{\"b\":4},\"c\":-1}"));
        assertEquals(0, Day12.part1("{\"a\":[-1,1]}"));
        assertEquals(0, Day12.part1("[-1,{\"a\":1}]"));
        assertEquals(0, Day12.part1("[]"));
        assertEquals(0, Day12.part1("{}"));

    }

    @Test
    void testPart2() {
        assertEquals(6, Day12.part2("[1,2,3]"));
        assertEquals(4, Day12.part2("[1,{\"c\":\"red\",\"b\":2},3]"));
        assertEquals(0, Day12.part2("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}"));
        assertEquals(6, Day12.part2("[1,\"red\",5]"));
    }
}
