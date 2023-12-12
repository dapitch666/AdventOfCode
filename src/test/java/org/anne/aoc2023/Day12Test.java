package org.anne.aoc2023;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Test {

    List<String> input = Arrays.asList(
            "???.### 1,1,3",
            ".??..??...?##. 1,1,3",
            "?#?#?#?#?#?#?#? 1,3,1,6",
            "????.#...#... 4,1,1",
            "????.######..#####. 1,6,5",
            "?###???????? 3,2,1"
    );
    
    @Test
    void testPart1() {
        assertEquals(1, Day12.getArrangements("???.###", Arrays.asList(1, 1, 3), new HashMap<>()));
        assertEquals(4, Day12.getArrangements(".??..??...?##.", Arrays.asList(1,1,3), new HashMap<>()));
        assertEquals(1, Day12.getArrangements("?#?#?#?#?#?#?#?", Arrays.asList(1,3,1,6), new HashMap<>()));
        assertEquals(1, Day12.getArrangements("????.#...#...", Arrays.asList(4,1,1), new HashMap<>()));
        assertEquals(4, Day12.getArrangements("????.######..#####.", Arrays.asList(1,6,5), new HashMap<>()));
        assertEquals(10, Day12.getArrangements("?###????????", Arrays.asList(3,2,1), new HashMap<>()));
        assertEquals(21, Day12.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(525152, Day12.part2(input));
    }
 }
