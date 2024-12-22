package org.anne.aoc2021;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.List;

public class Day16 extends Day {
    private static long versionSum;
    private static int currentCursor;

    public static void main(String[] args) {
        new Day16().run();
    }

    @Override
    public void execute() {
        setName("Packet Decoder");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static long part1(String input) {
        currentCursor = 0;
        versionSum = 0;
        parsePacket(hexToBits(input), Integer.MAX_VALUE);
        return versionSum;
    }

    public static long part2(String input) {
        currentCursor = 0;
        return parsePacket(hexToBits(input), Integer.MAX_VALUE).get(0);
    }

    private static List<Long> parsePacket(String binaryInput, int number) {
        List<Long> literals = new ArrayList<>();
        int cursor = 0;
        while(binaryInput.substring(cursor).length() > 6 && binaryInput.substring(cursor).contains("1") && number > 0) {
            number--;
            int version = Integer.parseInt(binaryInput.substring(cursor, cursor + 3), 2);
            versionSum += version;
            String typeId = binaryInput.substring(cursor + 3, cursor + 6);
            cursor += 6;
            if (typeId.equals("100")) {
                List<String> values = getGroups(binaryInput.substring(cursor));
                String joinedString = String.join("", values);
                literals.add(Long.parseLong(joinedString, 2));
                cursor += values.size() * 5;
            } else {
                boolean isPacketNbTypeId = binaryInput.charAt(cursor) == '1';
                cursor++;
                int subPacketsNb = Integer.MAX_VALUE;
                int totalLength = Integer.MAX_VALUE;
                List<Long> operationTerms;
                if (isPacketNbTypeId) {
                    subPacketsNb = Integer.parseInt(binaryInput.substring(cursor, cursor + 11), 2);
                    cursor += 11;
                    operationTerms = parsePacket(binaryInput.substring(cursor), subPacketsNb);
                } else {
                    totalLength = Integer.parseInt(binaryInput.substring(cursor, cursor + 15), 2);
                    cursor += 15;
                    operationTerms = parsePacket(binaryInput.substring(cursor, cursor + totalLength), subPacketsNb);
                }

                switch (typeId) {
                    case "000" -> literals.add(operationTerms.stream().mapToLong(l -> l).sum());
                    case "001" -> literals.add(operationTerms.stream().mapToLong(l -> l).reduce((a, b) -> a * b).orElse(0L));
                    case "010" -> literals.add(operationTerms.stream().mapToLong(l -> l).min().orElse(0L));
                    case "011" -> literals.add(operationTerms.stream().mapToLong(l -> l).max().orElse(0L));
                    case "101" -> literals.add(operationTerms.get(0) > operationTerms.get(1) ? 1L : 0L);
                    case "110" -> literals.add(operationTerms.get(0) < operationTerms.get(1) ? 1L : 0L);
                    case "111" -> literals.add(operationTerms.get(0).equals(operationTerms.get(1)) ? 1L : 0L);
                }
                cursor += isPacketNbTypeId ? currentCursor : totalLength;
            }
            currentCursor = cursor;
        }
        return literals;
    }

    static String hexToBits(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Character character : input.toCharArray()) {
            stringBuilder.append(String.format("%04d",
                    Integer.parseInt(Integer.toBinaryString(Character.digit(character, 16)))));
        }
        return stringBuilder.toString();
    }

    private static List<String> getGroups(String string) {
        List<String> groups = new ArrayList<>();
        while (string.length() >= 5) {
            if (string.charAt(0) == '1') {
                groups.add(string.substring(1, 5));
                string = string.substring(5);
            } else {
                groups.add(string.substring(1, 5));
                return groups;
            }
        }
        return groups;
    }
}
