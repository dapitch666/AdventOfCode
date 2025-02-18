package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Day21Test {

    Day21.Player player = new Day21.Player(8, 5, 5);
    Day21.Player boss = new Day21.Player(12, 7, 2);

    @Test
    void testPart1() {
        assertTrue(player.wins(boss));
    }
}
