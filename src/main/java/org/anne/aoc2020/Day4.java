package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day4 extends Day {

    public static void main(String[] args) {
        Day day = new Day4();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Passport Processing");
        List<String> input = parseInput(this.readFile());
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

     static long part1(List<String> input) {
        return input.stream().filter(i -> decodePassport(i).isValidPart1()).count();
    }

    static long part2(List<String> input) {
        return input.stream().filter(i -> decodePassport(i).isValidPart2()).count();
    }

    private record Passport(
            String birthYear,
            String issueYear,
            String expirationYear,
            String height,
            String hairColor,
            String eyeColor,
            String passportId
    ) {
        public boolean isValidPart1() {
            return birthYear != null && issueYear != null && expirationYear != null &&
                    height != null && hairColor != null && eyeColor != null && passportId != null;
        }

        public boolean isValidPart2() {
            if (isValidPart1()) {
                return validYear(birthYear, 1920, 2002) &&
                        validYear(issueYear, 2010, 2020) &&
                        validYear(expirationYear, 2020, 2030) &&
                        validHgt(height) &&
                        validHcl(hairColor) &&
                        validEcl(eyeColor) &&
                        validPid(passportId);
            }
            return false;
        }

        private boolean validPid(String passportId) {
            return passportId.matches("^\\d{9}$");
        }

        private boolean validEcl(String eyeColor) {
            List<String> eyeColorList = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
            return eyeColorList.contains(eyeColor);
        }

        private boolean validHcl(String hairColor) {
            return hairColor.matches("^#[0-9a-f]{6}$");
        }

        private boolean validHgt(String height) {
            String pattern = "^(\\d+)(in|cm)$";
            if (height.matches(pattern)) {
                int heightValue = Integer.parseInt(height.replaceAll(pattern, "$1"));
                String heightUnit = height.replaceAll(pattern, "$2");
                if (heightUnit.equals("in")) {
                    return heightValue >= 59 && heightValue <= 76;
                } else {
                    return heightValue >= 150 && heightValue <= 193;
                }
            }
            return false;
        }

        private boolean validYear(String yr, int min, int max) {
            if (yr.matches("\\d{4}")) {
                return Integer.parseInt(yr) >= min && Integer.parseInt(yr) <= max;
            }
            return false;
        }
    }

    static List<String> parseInput(List<String> input) {
        List<String> passports = new ArrayList<>();
        List<String> tempStr = new ArrayList<>();
        for (String line : input) {
            if (line.trim().isEmpty()) {
                passports.add(String.join(" ", tempStr));
                tempStr.clear();
            } else {
                tempStr.add(line);
            }
        }
        passports.add(String.join(" ", tempStr));
        return passports;
    }

    private static Passport decodePassport(String input) {
        HashMap<String, String> inputMap = new HashMap<>();
        String[] keyValues = input.split(" ");
        for (String keyVal : keyValues) {
            String[] parts = keyVal.split(":",2);
            inputMap.put(parts[0], parts[1]);
        }
        return new Passport(inputMap.get("byr"), inputMap.get("iyr"), inputMap.get("eyr"), inputMap.get("hgt"),
                inputMap.get("hcl"), inputMap.get("ecl"), inputMap.get("pid"));
    }
}
