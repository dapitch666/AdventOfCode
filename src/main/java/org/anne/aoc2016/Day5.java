package org.anne.aoc2016;

import org.anne.common.Day;
import org.anne.common.HashUtils;

import java.security.MessageDigest;
import java.util.concurrent.atomic.AtomicInteger;

public class Day5 extends Day {
    public static void main(String[] args) {
        new Day5().run();
    }

    @Override
    public void execute() {
        setName("How About a Nice Game of Chess?");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    private static final MessageDigest md5 = HashUtils.getMd5Instance();

    public static String part1(String input) {
        AtomicInteger index = new AtomicInteger(0);
        StringBuilder password = new StringBuilder();

        while (password.length() < 8) {
            password.append(findNextHash(input, index).charAt(5));
        }
        return password.toString().toLowerCase();
    }

    public static String part2(String input) {
        AtomicInteger index = new AtomicInteger(0);
        char[] password = new char[8];

        int digitsFound = 0;
        while (digitsFound < 8) {
            String result = findNextHash(input, index);
            int position = Character.getNumericValue(result.charAt(5));
            if ((position < 8) && (password[position] == '\u0000')) {
                password[position] = result.charAt(6);
                digitsFound++;
            }
        }
        return new String(password).toLowerCase();
    }

    private static String findNextHash(String input, AtomicInteger index) {
        StringBuilder sb = new StringBuilder(input);
        while (true) {
            sb.setLength(input.length());
            sb.append(index.getAndIncrement());
            md5.update(sb.toString().getBytes());
            byte[] result = md5.digest();
            if (result[0] == 0 && result[1] == 0 && (result[2] & 0xF0) == 0) {
                return HashUtils.bytesToHex(result);
            }
        }
    }
}
