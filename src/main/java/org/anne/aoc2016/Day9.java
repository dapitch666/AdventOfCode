package org.anne.aoc2016;

import org.anne.common.Day;

public class Day9 extends Day {
    public static void main(String[] args) {
        new Day9().run();
    }

    @Override
    public void execute() {
        setName("Explosives in Cyberspace");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static long part1(String input) {
        return decompressedLength(input, 1);
    }

    public static long part2(String input) {
        return decompressedLength(input, 2);
    }

    private static long decompressedLength(String input, int version) {
        long length = 0;
        for (int i = 0; i < input.length(); ) {
            if (input.charAt(i) == '(') {
                int j = input.indexOf(')', i);
                String[] parts = input.substring(i + 1, j).split("x");
                i = j + 1;
                int nbChars = Integer.parseInt(parts[0]);
                int times = Integer.parseInt(parts[1]);
                String data = input.substring(i, i + nbChars);
                length += times * (version == 2 ? decompressedLength(data, version) : data.length());
                i += nbChars;
            } else {
                length++;
                i++;
            }
        }
        return length;
    }
}
