package org.anne.aoc2022;

import org.anne.common.Day;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Day13 extends Day {
    public static void main(String[] args) {
        Day day = new Day13();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static int part1(List<String> input) {
        int sumOfValidPairs = 0;
        for (int i = 0; i < input.size(); i += 3) {
            Packet left = new Packet(input.get(i));
            Packet right = new Packet(input.get(i+1));
            if (left.compareTo(right) > 0) {
                sumOfValidPairs += i / 3 + 1;
            }
        }
        return sumOfValidPairs;
    }

    public static int part2(List<String> input) {
        Packet two = new Packet("[[2]]");
        Packet six = new Packet("[[6]]");
        List<Packet> packets = Stream
                .concat(
                    input.stream().filter(s -> !s.equals("")).map(Packet::new),
                    Stream.of(two, six))
                .sorted(Comparator.reverseOrder())
                .toList();
        return (packets.indexOf(two) + 1) * (packets.indexOf(six) + 1);
    }

    static class Packet implements Comparable<Packet> {
        private final List<Packet> children = new ArrayList<>();
        private String value;

        public Packet(String packet) {
            if (packet.startsWith("[")) {
                packet = packet.substring(1, packet.length() - 1);
                int level = 0;
                StringBuilder sb = new StringBuilder();
                for (char c : packet.toCharArray()) {
                    if (c == ',' && level == 0) {
                        children.add(new Packet(sb.toString()));
                        sb = new StringBuilder();
                    } else {
                        level += (c == '[') ? 1 : (c == ']') ? -1 : 0;
                        sb.append(c);
                    }
                }
                children.add(new Packet(sb.toString()));
            } else {
                value = packet;
            }
        }

        public boolean isInt() {
            return this.children.size() == 0 && !value.equals("");
        }

        @Override
        public int compareTo(@NotNull Packet o) {
             if (this.isInt() && o.isInt()) {
                return Integer.parseInt(o.value) - Integer.parseInt(this.value);
            }
            if (!this.isInt() && !o.isInt()) {
                for (int i = 0; i < Math.min(this.children.size(), o.children.size()); i++) {
                    int value = this.children.get(i).compareTo(o.children.get(i));
                    if (value != 0) {
                        return value;
                    }
                }
                return o.children.size() - this.children.size();
            }
            Packet lst1 = this.isInt() ? new Packet("[" + value + "]") : this;
            Packet lst2 = o.isInt() ? new Packet("[" + o.value + "]") : o;
            return lst1.compareTo(lst2);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (isInt()) {
                sb = new StringBuilder(value);
            } else if (this.children.size() > 0) {
                sb.append(String.join(",", this.children.toString()));
            }
            return sb.toString();
        }
    }
}
