package org.anne.aoc2023;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day25Test {

    List<String> input = Arrays.asList(
            "jqt: rhn xhk nvd",
            "rsh: frs pzl lsr",
            "xhk: hfx",
            "cmg: qnr nvd lhk bvb",
            "rhn: xhk bvb hfx",
            "bvb: xhk hfx",
            "pzl: lsr hfx nvd",
            "qnr: nvd",
            "ntq: jqt hfx bvb xhk",
            "nvd: lhk",
            "lsr: lhk",
            "rzs: qnr cmg lsr rsh",
            "frs: qnr lhk lsr"
    );
    
    @Test
    void testPart1() {
        assertEquals(54, Day25.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals("Merry Christmas!", Day25.part2());
    }
}
