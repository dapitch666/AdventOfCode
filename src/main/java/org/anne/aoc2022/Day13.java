package org.anne.aoc2022;

import org.anne.common.Day;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Day13 extends Day {
    public static void main(String[] args) {
        new Day13().run();
    }

    @Override
    public void execute() {
        setName("Distress Signal");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
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
                    input.stream().filter(s -> !s.isEmpty()).map(Packet::new),
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
            return children.isEmpty() && !value.isEmpty();
        }

        @Override
        public int compareTo(@NotNull Packet o) {
             if (isInt() && o.isInt()) {
                return Integer.parseInt(o.value) - Integer.parseInt(value);
            }
            if (!isInt() && !o.isInt()) {
                for (int i = 0; i < Math.min(children.size(), o.children.size()); i++) {
                    int value = children.get(i).compareTo(o.children.get(i));
                    if (value != 0) {
                        return value;
                    }
                }
                return o.children.size() - children.size();
            }
            Packet lst1 = isInt() ? new Packet("[" + value + "]") : this;
            Packet lst2 = o.isInt() ? new Packet("[" + o.value + "]") : o;
            return lst1.compareTo(lst2);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (isInt()) {
                sb = new StringBuilder(value);
            } else if (!children.isEmpty()) {
                sb.append(String.join(",", children.toString()));
            }
            return sb.toString();
        }
    }
}
