package org.anne.aoc2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day16Test {

    @Test
    void testPart1() {
        assertEquals(6L, Day16.part1("D2FE28"));
        assertEquals(9L, Day16.part1("38006F45291200"));
        assertEquals(14L, Day16.part1("EE00D40C823060"));
        assertEquals(16, Day16.part1("8A004A801A8002F478"));
        assertEquals(12, Day16.part1("620080001611562C8802118E34"));
        assertEquals(23, Day16.part1("C0015000016115A2E0802F182340"));
        assertEquals(31, Day16.part1("A0016C880162017C3686B18A3D4780"));
    }

    @Test
    void testPart2() {
        assertEquals(3L, Day16.part2("C200B40A82")); // sum of 1 and 2
        assertEquals(54L, Day16.part2("04005AC33890")); // product of 6 and 9
        assertEquals(7L, Day16.part2("880086C3E88112")); // minimum of 7, 8, and 9
        assertEquals(9L, Day16.part2("CE00C43D881120")); // maximum of 7, 8, and 9
        assertEquals(1L, Day16.part2("D8005AC2A8F0")); // because 5 is less than 15
        assertEquals(0L, Day16.part2("F600BC2D8F")); // because 5 is not greater than 15
        assertEquals(0L, Day16.part2("9C005AC2F8F0")); // because 5 is not equal to 15
        assertEquals(1L, Day16.part2("9C0141080250320F1802104A08")); // because 1 + 3 = 2 * 2
    }

    @Test
    void testHexToBits() {
        assertEquals("110100101111111000101000", Day16.hexToBits("D2FE28"));
    }
}

