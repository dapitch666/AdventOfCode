package org.anne.aoc2016;

import org.anne.common.Day;

public class Day16 extends Day {
    public static void main(String[] args) {
        new Day16().run();
    }

    @Override
    public void execute() {
        setName("Dragon Checksum");
        String input = readFileOneLine();
        setPart1(part1(input, 272));
        setPart2(part2(input, 35651584));
    }

    public static String part1(String input, int diskSize) {
        return checksum(generateData(input, diskSize));
    }

    public static String part2(String input, int diskSize) {
        return checksum(generateData(input, diskSize));
    }

    private static String generateData(String input, int diskSize) {
        char[] data = input.toCharArray();
        while (data.length < diskSize) {
            int length = data.length;
            char[] newData = new char[length * 2 + 1];
            System.arraycopy(data, 0, newData, 0, length);
            newData[length] = '0';
            for (int i = 0; i < length; i++) {
                newData[length + 1 + i] = data[length - 1 - i] == '0' ? '1' : '0';
            }
            data = newData;
        }
        return checksum(new String(data, 0, diskSize));
    }

    private static String checksum(String data) {
        char[] chars = data.toCharArray();
        while (chars.length % 2 == 0) {
            int newLength = chars.length / 2;
            char[] newChecksum = new char[newLength];
            for (int i = 0; i < newLength; i++) {
                newChecksum[i] = (chars[2 * i] == chars[2 * i + 1]) ? '1' : '0';
            }
            chars = newChecksum;
        }
        return new String(chars);
    }
}