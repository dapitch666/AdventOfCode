package org.anne.aoc2023;

import org.anne.common.Day;
import org.anne.common.Direction;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Day16 extends Day {
    public static void main(String[] args) {
        Day day = new Day16();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }


    public static long part1(List<String> input) {
        return getEnergizedCount(getMap(input), new Beam(new Point(-1, 0), Direction.EAST));
    }

    public static long part2(List<String> input) {
        var map = getMap(input);
        
        var startingBeams = new ArrayList<Beam>();
        startingBeams.addAll(map.keySet()
                .stream()
                .filter(p -> p.y == 0)
                .map(p -> new Beam(new Point(p.x, -1), Direction.SOUTH))
                .toList());
        startingBeams.addAll(map.keySet()
                .stream()
                .filter(p -> p.x == 0)
                .map(p -> new Beam(new Point(-1, p.y), Direction.EAST))
                .toList());
        startingBeams.addAll(map.keySet()
                .stream()
                .filter(p -> p.y == input.size()-1)
                .map(p -> new Beam(new Point(p.x, p.y+1), Direction.NORTH))
                .toList());
        startingBeams.addAll(map.keySet()
                .stream()
                .filter(p -> p.x == input.get(0).length()-1)
                .map(p -> new Beam(new Point(p.x+1, p.y), Direction.WEST))
                .toList());
        return startingBeams.stream().parallel().mapToLong(b -> getEnergizedCount(map, b)).max().orElse(0L);
    }

    private static List<Beam> getNext(Map<Point, Character> map, Beam beam) {
        var next = Direction.getPoint(beam.direction, beam.point);
        if (!map.containsKey(next)) {
            return List.of();
        }
        switch (map.get(next)) {
            case '/' -> {
                if (beam.direction() == Direction.NORTH || beam.direction() == Direction.SOUTH) {
                    return List.of(new Beam(next, Direction.rotate90(beam.direction(), true)));
                } else {
                    return List.of(new Beam(next, Direction.rotate90(beam.direction(), false)));
                }
            }
            case '\\' -> {
                if (beam.direction() == Direction.NORTH || beam.direction() == Direction.SOUTH) {
                    return List.of(new Beam(next, Direction.rotate90(beam.direction(), false)));
                } else {
                    return List.of(new Beam(next, Direction.rotate90(beam.direction(), true)));
                }
            }
            case '|' -> {
                if (beam.direction() == Direction.NORTH || beam.direction() == Direction.SOUTH) {
                    return List.of(new Beam(next, beam.direction()));
                } else {
                    return List.of(new Beam(next, Direction.NORTH), new Beam(next, Direction.SOUTH));
                }
            }
            case '-' -> {
                if (beam.direction() == Direction.EAST || beam.direction() == Direction.WEST) {
                    return List.of(new Beam(next, beam.direction()));
                } else {
                    return List.of(new Beam(next, Direction.EAST), new Beam(next, Direction.WEST));
                }
            }
            case '.' -> {
                return List.of(new Beam(next, beam.direction()));
            }
            default -> throw new IllegalStateException("Unexpected value: " + map.get(next));
        }
    }

    private static long getEnergizedCount(Map<Point, Character> map, Beam start) {
        var energized = new HashSet<Beam>();
        var queue = new LinkedList<>(getNext(map, start));
        while (!queue.isEmpty()) {
            var beam = queue.poll();
            if (energized.contains(beam)) {
                continue;
            }
            energized.add(beam);
            queue.addAll(getNext(map, beam));
        }
        return energized.stream().map(Beam::point).distinct().count();
    }

    record Beam (Point point, Direction direction) {}
    
    static Map<Point, Character> getMap(List<String> input) {
        var map = new HashMap<Point, Character>();
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(0).length(); x++) {
                map.put(new Point(x, y), input.get(y).charAt(x));
            }
        }
        return map;
    }
}
