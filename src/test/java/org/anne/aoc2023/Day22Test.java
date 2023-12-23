package org.anne.aoc2023;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day22Test {

    List<String> input = Arrays.asList(
            "1,0,1~1,2,1~A",
            "0,0,2~2,0,2~B",
            "0,2,3~2,2,3~C",
            "0,0,4~0,2,4~D",
            "2,0,5~2,2,5~E",
            "0,1,6~2,1,6~F",
            "1,1,8~1,1,9~G"
    );
    
    @Test
    void testPart1() {
        assertEquals(5, Day22.part1(input));
    }

    @Test
    void testPart2() {
    //    assertEquals(7, Day22.part2(input));
    }
}
