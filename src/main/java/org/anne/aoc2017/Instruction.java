package org.anne.aoc2017;

record Instruction(String op, char target, Character source, Long value) {
    public static Instruction parse(String s) {
        String[] parts = s.split(" ");
        char target = parts[1].charAt(0);
        Long value = null;
        Character source = null;
        if (parts.length == 3) {
            if (Character.isAlphabetic(parts[2].charAt(0))) {
                source = parts[2].charAt(0);
            } else {
                value = Long.parseLong(parts[2]);
            }
        }
        return new Instruction(parts[0], target, source, value);
    }
}