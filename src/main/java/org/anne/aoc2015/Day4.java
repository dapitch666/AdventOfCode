package org.anne.aoc2015;

import org.anne.common.Day;
import org.anne.common.HashUtils;

import java.security.MessageDigest;
import java.util.List;

public class Day4 extends Day {
    public static void main(String[] args) {
        new Day4().run();
    }

    @Override
    public void execute() {
        setName("The Ideal Stocking Stuffer");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    private static final MessageDigest md5 = HashUtils.getMd5Instance();

    public static int part1(String input) {
        StringBuilder sb = new StringBuilder(input);
        int i = 1;
        while (true) {
            sb.setLength(input.length());
            sb.append(i);
            md5.update(sb.toString().getBytes());
            byte[] result = md5.digest();
            if (result[0] == 0 && result[1] == 0 && (result[2] & 0xF0) == 0) {
                return i;
            }
            i++;
        }
    }

    public static int part2(String input) {
        StringBuilder sb = new StringBuilder(input);
        int i = 1;
        while (true) {
            sb.setLength(input.length());
            sb.append(i);
            md5.update(sb.toString().getBytes());
            byte[] result = md5.digest();
            if (result[0] == 0 && result[1] == 0 && result[2] == 0) {
                return i;
            }
            i++;
        }
    }
}
